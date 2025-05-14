/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.mapper;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface BaseMapper.
 *
 * @param <D> the generic type
 * @param <E> the element type
 */
public interface BaseMapper<D, E> {

	   /**
   	 * To response.
   	 *
   	 * @param e the e
   	 * @return the d
   	 */
   	D toResponse(E e);

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
   	List<D> toResponse(List<E> eList);

	   /**
   	 * To domain.
   	 *
   	 * @param dList the d list
   	 * @return the list
   	 */
   	List<E> toDomain(List<D> dList);
	}
