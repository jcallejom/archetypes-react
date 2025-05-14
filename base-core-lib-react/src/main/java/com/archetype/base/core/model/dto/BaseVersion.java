/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.model.dto;

import java.io.Serializable;


// TODO: Auto-generated Javadoc
/**
 * The Class BaseVersion.
 */
public abstract class BaseVersion implements Serializable {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The version. */
	protected Long version;

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version the new version
	 */
	public void setVersion(final Long version) {
		this.version = version;
	}
}
