/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.archetype.base.core.exception.model.BaseError;

// TODO: Auto-generated Javadoc
/**
 * The Class MicroserviceRuntimeException.
 */
public abstract class MicroserviceRuntimeException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The http code. */
	private final Integer httpCode;
	
	/** The error code. */
	private final String errorCode;
	
	/** The parameters. */
	private final List<Object> parameters = new ArrayList<Object>();

	/**
	 * Instantiates a new microservice runtime exception.
	 *
	 * @param error the error
	 * @param cause the cause
	 * @param parameters the parameters
	 */
	public MicroserviceRuntimeException(final BaseError error, final Throwable cause, final Object... parameters) {
		super(error.getMessage(), cause);
		this.errorCode = error.getCode();
		this.httpCode = error.getHttpCode();
		Arrays.stream(parameters).forEach(this.getParameters()::add);
	}

	/**
	 * Instantiates a new microservice runtime exception.
	 *
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 * @param httpCode the http code
	 * @param cause the cause
	 * @param parameters the parameters
	 */
	public MicroserviceRuntimeException(final String errorCode, final String errorMessage, final Integer httpCode,
			final Throwable cause, final Object... parameters) {
		super(errorMessage, cause);
		this.errorCode = errorCode;
		this.httpCode = httpCode;
		Arrays.stream(parameters).forEach(this.getParameters()::add);
	}

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode() {
		return this.errorCode;
	}

	/**
	 * Gets the parameters.
	 *
	 * @return the parameters
	 */
	public List<Object> getParameters() {
		return this.parameters;
	}
	
	/**
	 * Gets the http code.
	 *
	 * @return the http code
	 */
	public Integer getHttpCode() {
		return httpCode;
	}
}
