/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.exception.model;

// TODO: Auto-generated Javadoc
/**
 * The Interface BaseError.
 */
public  interface BaseError {

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	String getCode();

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	String getMessage();

	/**
	 * Gets the http code.
	 *
	 * @return the http code
	 */
	Integer getHttpCode();

	/**
	 * Checks if is technical.
	 *
	 * @return true, if is technical
	 */
	default boolean isTechnical() {
			return this.getCode().startsWith("T-");
		}

	/**
	 * Checks if is functional.
	 *
	 * @return true, if is functional
	 */
	default boolean isFunctional() {
			return this.getCode().startsWith("F-");
		}
}
