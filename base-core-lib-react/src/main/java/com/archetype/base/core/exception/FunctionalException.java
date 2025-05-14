/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.exception;

import com.archetype.base.core.exception.model.BaseError;

// TODO: Auto-generated Javadoc
/**
 * The Class FunctionalException.
 */
public class FunctionalException extends MicroserviceException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new functional exception.
	 *
	 * @param error the error
	 */
	public FunctionalException(final BaseError error) {
		super(error, null, "");
	}

	/**
	 * Instantiates a new functional exception.
	 *
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 * @param httpCode the http code
	 */
	public FunctionalException(final String errorCode, final String errorMessage, final Integer httpCode) {
		super(errorCode, errorMessage, httpCode, null, "");
	}

	/**
	 * Instantiates a new functional exception.
	 *
	 * @param error the error
	 * @param cause the cause
	 * @param vars the vars
	 */
	public FunctionalException(final BaseError error, final Throwable cause, final Object... vars) {
		super(error, cause, vars);
	}

	/**
	 * Instantiates a new functional exception.
	 *
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 * @param httpCode the http code
	 * @param cause the cause
	 * @param vars the vars
	 */
	public FunctionalException(final String errorCode, final String errorMessage,  final Integer httpCode,
			final Throwable cause, final Object... vars) {
		super(errorCode, errorMessage, httpCode, cause, vars);
	}
}
