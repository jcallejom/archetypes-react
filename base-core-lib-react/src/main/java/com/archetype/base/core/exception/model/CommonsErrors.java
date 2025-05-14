/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.exception.model;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

import com.archetype.base.core.utils.MessageSourceHelper;

// TODO: Auto-generated Javadoc
/**
 * The Enum CommonsErrors.
 */
public enum CommonsErrors implements BaseError {
	
	/** The exception com server no available. */
	EXCEPTION_COM_SERVER_NO_AVAILABLE("T-201", HttpStatus.SERVICE_UNAVAILABLE),
	
	/** The exception com server timeout. */
	EXCEPTION_COM_SERVER_TIMEOUT("T-202", HttpStatus.SERVICE_UNAVAILABLE),
	
	/** The exception com authentication error. */
	EXCEPTION_COM_AUTHENTICATION_ERROR("T-203", HttpStatus.FORBIDDEN);

	/** The code. */
	private final String code;

/** The http code. */
//	private final String message;
	private final Integer httpCode;

	/**
	 * Instantiates a new commons errors.
	 *
	 * @param code the code
	 * @param httpCode the http code
	 */
	CommonsErrors(final String code, final HttpStatus httpCode) {
		this.code = code;
	//	this.message = message;
		this.httpCode = httpCode.value();
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		//return this.message;
		return MessageSourceHelper.getMessage(this.name(), null, LocaleContextHolder.getLocale());
	}

	/**
	 * Gets the http code.
	 *
	 * @return the http code
	 */
	public Integer getHttpCode() {
		return this.httpCode;
	}
}
