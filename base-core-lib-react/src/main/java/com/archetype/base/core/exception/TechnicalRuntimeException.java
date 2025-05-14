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
 * The Class TechnicalRuntimeException.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TechnicalRuntimeException extends MicroserviceRuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new technical runtime exception.
	 *
	 * @param error the error
	 */
	public TechnicalRuntimeException(final BaseError error) {
		super(error, null, new Object[0]);
	}
	
	/**
	 * Instantiates a new technical runtime exception.
	 *
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 */
	public TechnicalRuntimeException(final String errorCode, final String errorMessage) {
		super(errorCode, errorMessage, GenericError.EXCEPTION_COM_INTERNAL_ERROR.getHttpCode(), null, new Object[0]);
	}
	public TechnicalRuntimeException(final String errorCode, final String errorMessage, final Integer httpCode) {
		super(errorCode, errorMessage, httpCode, null, "");
	}
	/**
	 * Instantiates a new technical runtime exception.
	 *
	 * @param error the error
	 * @param cause the cause
	 * @param vars the vars
	 */
	public TechnicalRuntimeException(final BaseError error, final Throwable cause, final Object... vars) {
		super(error, cause, vars);
	}

	/**
	 * Instantiates a new technical runtime exception.
	 *
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 * @param cause the cause
	 * @param vars the vars
	 */
	public TechnicalRuntimeException(final String errorCode, final String errorMessage, final Throwable cause,
			final Object... vars) {
		super(errorCode, errorMessage, GenericError.EXCEPTION_COM_INTERNAL_ERROR.getHttpCode(), cause, vars);
	}
	public TechnicalRuntimeException(final String errorCode, final String errorMessage,  final Integer httpCode,
			final Throwable cause, final Object... vars) {
		super(errorCode, errorMessage, httpCode, cause, vars);
	}
}
