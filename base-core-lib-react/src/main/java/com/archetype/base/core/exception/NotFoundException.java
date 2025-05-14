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
 * The Class NotFoundException.
 */
//@ResponseStatus(GenericError.EXCEPTION_COM_ELEMENT_NOT_FOUND.getHttpCode())
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends TechnicalRuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new not found exception.
	 *
	 * @param error the error
	 */
	public NotFoundException(final BaseError error) {
		super(error);
	}

	/**
	 * Instantiates a new not found exception.
	 *
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 */
	public NotFoundException(final String errorCode, final String errorMessage) {
		super(errorCode, errorMessage, GenericError.EXCEPTION_COM_ELEMENT_NOT_FOUND.getHttpCode());
	}
	public NotFoundException(final String errorCode, final String errorMessage, final Integer httpCode) {
		super(errorCode, errorMessage, httpCode);
	}
	/**
	 * Instantiates a new not found exception.
	 *
	 * @param error the error
	 * @param cause the cause
	 * @param vars the vars
	 */
	public NotFoundException(final BaseError error, final Throwable cause, final Object... vars) {
		super(error, cause, vars);
	}

	/**
	 * Instantiates a new not found exception.
	 *
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 * @param cause the cause
	 * @param vars the vars
	 */
	public NotFoundException(final String errorCode, final String errorMessage, final Throwable cause,
			final Object... vars) {
		super(errorCode, errorMessage, GenericError.EXCEPTION_COM_ELEMENT_NOT_FOUND.getHttpCode(),
				cause, vars);
	}

}
