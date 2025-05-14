/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.utils;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseRoles.
 */
public  final class BaseRoles {
	// public static final String ROLE_FRONTOFFICE = "ROLE_FRONTOFFICE";
	//public static final String ROLE_BACKOFFICE = "ROLE_BACKOFFICE";
	/** The Constant ROLE_ANONYMOUS. */
	/*Roles Usados por las SPAs*/
	public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";
	
	/** The Constant ROLE_ADMIN. */
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	/**
	 * Instantiates a new base roles.
	 */
	private BaseRoles() {
		super();
	}

	/**
	 * Gets the role anonymous.
	 *
	 * @return the role anonymous
	 */
	public static String getRoleAnonymous() {
		return ROLE_ANONYMOUS;
	}

	/**
	 * Gets the role admin.
	 *
	 * @return the role admin
	 */
	public static String getRoleAdmin() {
		return ROLE_ADMIN;
	}


}
