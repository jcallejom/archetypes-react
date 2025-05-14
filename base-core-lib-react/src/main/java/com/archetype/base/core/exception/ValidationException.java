/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.archetype.base.core.exception.model.BaseError;
import com.archetype.base.core.exception.model.GenericError;

/**
 * The Class ValidationException.
 */
//@ResponseStatus( HttpStatus.valueOf(GenericError.EXCEPTION_COM_INVALID_DATA_FORMAT.getHttpCode()))
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends TechnicalRuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new validation exception.
	 *
	 * @param error the error
	 */
	public ValidationException(final BaseError error) {
		super(error);
	}

	/**
	 * Instantiates a new validation exception.
	 *
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 */
	public ValidationException(final String errorCode, final String errorMessage) {
		super(errorCode, errorMessage,GenericError.EXCEPTION_COM_INVALID_DATA_FORMAT.getHttpCode() );
	}

	/**
	 * Instantiates a new validation exception.
	 *
	 * @param error the error
	 * @param cause the cause
	 * @param vars the vars
	 */
	public ValidationException(final BaseError error, final Throwable cause, final Object... vars) {
		super(error, cause, vars);
	}
	public ValidationException(final String errorCode, final String errorMessage, final Integer httpCode) {
		super(errorCode, errorMessage, httpCode);
	}
	/**
	 * Instantiates a new validation exception.
	 *
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 * @param cause the cause
	 * @param vars the vars
	 */
	public ValidationException(final String errorCode, final String errorMessage, final Throwable cause,
			final Object... vars) {
		super(errorCode, errorMessage, GenericError.EXCEPTION_COM_INVALID_DATA_FORMAT.getHttpCode(),
				cause, vars);
	}
	
}
