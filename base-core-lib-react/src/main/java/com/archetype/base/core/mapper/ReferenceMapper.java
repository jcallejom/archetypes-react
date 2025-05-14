/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.mapper;



import java.lang.reflect.InvocationTargetException;

import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.ApplicationScope;

import com.archetype.base.core.model.BaseEntity;
import com.archetype.base.core.model.dto.BaseDTO;
import com.archetype.base.core.repository.BaseRelationalRepository;


// TODO: Auto-generated Javadoc
/**
 * The Class ReferenceMapper.
 *
 * @param <ID> the generic type
 * @param <T> the generic type
 */
@ApplicationScope
public class ReferenceMapper<ID,T> {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ReferenceMapper.class);

	/** The entity manager. */
	@Autowired
	private BaseRelationalRepository baseRelationalRepository;

	/**
	 * Resolve.
	 *
	 * @param <T> the generic type
	 * @param sourceDto the source dto
	 * @param type the type
	 * @return the t
	 */
	@ObjectFactory
	public <T extends BaseEntity> T resolve(final BaseDTO<ID> sourceDto, @TargetType final Class<T> type) {
		T entity = (T) baseRelationalRepository.findById(sourceDto.getId());
		try {
//			return entity != null ? entity : type.newInstance();
			return entity != null ? entity : type.getDeclaredConstructor().newInstance();

//		} catch (InstantiationException | IllegalAccessException e) {
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
			LOG.error(e.getMessage());
		}

		return entity;
	}

	/**
	 * Map.
	 *
	 * @param id the id
	 * @return the integer
	 */
	public Integer map(final Object id) {
		return (Integer) id;
	}

	/**
	 * Map.
	 *
	 * @param id the id
	 * @return the object
	 */
	public Object map(final Integer id) {
		return id;
	}
}
