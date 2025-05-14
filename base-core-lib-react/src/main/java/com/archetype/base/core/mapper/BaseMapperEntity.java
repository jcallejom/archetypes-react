/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

// TODO: Auto-generated Javadoc
/**
 * The Interface BaseMapperEntity.
 *
 * @param <D> the generic type
 * @param <E> the element type
 */
public interface BaseMapperEntity<D, E> {
	   
	   /**
   	 * To response.
   	 *
   	 * @param e the e
   	 * @return the d
   	 */
   	@InheritInverseConfiguration
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
   	@InheritInverseConfiguration
	   List<D> toResponse(List<E> eList);

	   /**
   	 * To domain.
   	 *
   	 * @param dList the d list
   	 * @return the list
   	 */
   	List<E> toDomain(List<D> dList);
	   
	   /**
   	 * Clone.
   	 *
   	 * @param source the source
   	 * @param target the target
   	 */
   	@Mapping(target = "target.id", ignore = true)
	   void clone(E source, @MappingTarget E target);

	   /**
   	 * Clon.
   	 *
   	 * @param source the source
   	 * @param target the target
   	 * @return the e
   	 */
   	@Mapping(target = "target.id", ignore = true)
	   E clon(E source, @MappingTarget E target);
	}
