/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

/**
 * The Interface BaseRepository.
 *
 * @param <T> the generic type
 * @param <ID> the generic type
 */
public interface BaseNoRelationalRepository<T, ID> extends R2dbcRepository<T, ID> {

}
