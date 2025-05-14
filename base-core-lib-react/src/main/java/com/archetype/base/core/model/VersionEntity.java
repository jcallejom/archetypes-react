/**
 * Ayesa
 * @author jcallejo
 */


package com.archetype.base.core.model;

import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;

// TODO: Auto-generated Javadoc
/**
 * The Class VersionEntity.
 */
public abstract class VersionEntity implements BaseEntity {
	
	/** The version. */
	@Version
	@Column(value = "version")
	protected long version;

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public long getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version the new version
	 */
	public void setVersion(final long version) {
		this.version = version;
	}



}
