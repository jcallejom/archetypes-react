/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.mapper;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface BaseMapperVo.
 *
 * @param <D> the generic type
 * @param <E> the element type
 * @param <F> the generic type
 */
public interface BaseMapperVo<D, E, F> {

	   /**
   	 * To response.
   	 *
   	 * @param e the e
   	 * @return the f
   	 */
   	F toResponse(E e);

	   /**
   	 * To domain.
   	 *
   	 * @param d the d
   	 * @return the e
   	 */
   	E toDomain(D d);

	   /**
   	 * To response.
   	 *
   	 * @param eList the e list
   	 * @return the list
   	 */
   	List<F> toResponse(List<E> eList);

	   /**
   	 * To domain.
   	 *
   	 * @param dList the d list
   	 * @return the list
   	 */
   	List<E> toDomain(List<D> dList);
	}
