/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.model.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseDTO.
 *
 * @param <T> the generic type
 */
public abstract class BaseDTO<T> extends BaseVersion {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	protected T id;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public T getId() {
        return id;
    }
    
    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final T id) {
        this.id = id;
    }
}
