/* 
 * Spoon - http://spoon.gforge.inria.fr/
 * Copyright (C) 2006 INRIA Futurs <renaud.pawlak@inria.fr>
 * 
 * This software is governed by the CeCILL-C License under French law and
 * abiding by the rules of distribution of free software. You can use, modify 
 * and/or redistribute the software under the terms of the CeCILL-C license as 
 * circulated by CEA, CNRS and INRIA at http://www.cecill.info. 
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE. See the CeCILL-C License for more details.
 *  
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL-C license and that you accept its terms.
 */

package spoon.reflect.declaration;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

import spoon.processing.FactoryAccessor;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.CtVisitor;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.Root;

/**
 * This interface is the root interface for the metamodel elements (any program
 * element).
 */
@Root
public interface CtElement extends FactoryAccessor, Comparable<CtElement> {
	/**
	 * Accepts a visitor.
	 * 
	 * @param visitor
	 *            to accept
	 */
	void accept(CtVisitor visitor);

	/**
	 * Searches for an annotation of the given class that annotates the current
	 * element.
	 * 
	 * @param <A>
	 *            the annotation's type
	 * @param annotationType
	 *            the annotation's class
	 * @return if found, returns a proxy for this annotation
	 */
	<A extends Annotation> A getAnnotation(Class<A> annotationType);

	/**
	 * Gets the annotation element for a given annotation type.
	 * 
	 * @param annotationType
	 *            the annotation type
	 * @return the annotation if this element is annotated by one annotation of
	 *         the given type
	 */
	<A extends Annotation> CtAnnotation<A> getAnnotation(
			CtTypeReference<A> annotationType);

	/**
	 * Returns the annotations that are present on this element.
	 */
	Set<CtAnnotation<? extends Annotation>> getAnnotations();

	/**
	 * Returns the text of the documentation ("javadoc") comment of this
	 * element.
	 */
	String getDocComment();

	/**
	 * Gets the parent of current element.
	 */
	CtElement getParent();

	/**
	 * Gets the signature of the element.
	 */
	String getSignature();

	/**
	 * Gets the first parent that matches the given type.
	 */
	<P extends CtElement> P getParent(Class<P> parentType);

	/**
	 * Tells if the given element is a direct or indirect parent.
	 */
	boolean hasParent(CtElement candidate);

	/**
	 * Gets the position of this element in input source files
	 * 
	 * @return Source file and line number of this element or null
	 */
	SourcePosition getPosition();

	/**
	 * Replaces this element by another one.
	 */
	void replace(CtElement element);

	/**
	 * Replaces the elements that match the filter by the given element.
	 */
	void replace(Filter<? extends CtElement> replacementPoints,
			CtElement element);

	/**
	 * Sets the annotations for this element.
	 */
	void setAnnotations(Set<CtAnnotation<? extends Annotation>> annotation);

	/**
	 * Sets the text of the documentation ("javadoc") comment of this
	 * declaration.
	 */
	void setDocComment(String docComment);

	/**
	 * Sets the parent element of the current element.
	 * 
	 * @param element
	 *            parent
	 */
	void setParent(CtElement element);

	/**
	 * Sets the position in the Java source file.
	 * 
	 * @param position
	 *            of this element in the input source files
	 */
	void setPosition(SourcePosition position);

	/**
	 * Gets the child elements annotated with the given annotation type's
	 * instances.
	 * 
	 * @param <E>
	 *            the element's type
	 * @param annotationType
	 *            the annotation type
	 * @return all the child elements annotated with an instance of the given
	 *         annotation type
	 */
	<E extends CtElement> List<E> getAnnotatedChildren(
			Class<? extends Annotation> annotationType);

	/**
	 * Returns true if this element is implicit and automatically added by the
	 * Java compiler.
	 */
	boolean isImplicit();

	/**
	 * Sets this element to be implicit (will not be printed).
	 */
	void setImplicit(boolean b);
	
}