/*
 * @author jcallejo
 */
package com.archetype.base.core.exception.model;



import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

import com.archetype.base.core.utils.MessageSourceHelper;

// TODO: Auto-generated Javadoc
/**
 * The Enum GenericError.
 */
public enum GenericError implements BaseError {
	
	/** The exception com internal error. */
	EXCEPTION_COM_INTERNAL_ERROR("T-GENERIC-100",
		HttpStatus.INTERNAL_SERVER_ERROR),
	
	/** The exception com network error. */
	EXCEPTION_COM_NETWORK_ERROR("T-GENERIC-101",
		HttpStatus.INTERNAL_SERVER_ERROR),
	
	/** The exception com database error. */
	EXCEPTION_COM_DATABASE_ERROR("T-GENERIC-102",
		HttpStatus.INTERNAL_SERVER_ERROR),
	
	/** The exception com security error. */
	EXCEPTION_COM_SECURITY_ERROR("T-GENERIC-103",
		HttpStatus.UNAUTHORIZED),
	
	/** The exception com jwt token invalid. */
	EXCEPTION_COM_JWT_TOKEN_INVALID("T-GENERIC-1031",
		HttpStatus.INTERNAL_SERVER_ERROR),
	
	/** The exception com jwt token access denied. */
	EXCEPTION_COM_JWT_TOKEN_ACCESS_DENIED("T-GENERIC-1032",
		HttpStatus.INTERNAL_SERVER_ERROR),
	
	/** The exception com jwt token invalid signature. */
	EXCEPTION_COM_JWT_TOKEN_INVALID_SIGNATURE("T-GENERIC-1033",
		HttpStatus.INTERNAL_SERVER_ERROR),
	
	/** The exception com component unavailable. */
	EXCEPTION_COM_COMPONENT_UNAVAILABLE("T-GENERIC-104",
		HttpStatus.SERVICE_UNAVAILABLE),
	
	/** The exception com service unavailable. */
	EXCEPTION_COM_SERVICE_UNAVAILABLE("T-GENERIC-105",
		HttpStatus.SERVICE_UNAVAILABLE),
	
	/** The exception com circuit opened. */
	EXCEPTION_COM_CIRCUIT_OPENED("T-GENERIC-106",
		HttpStatus.SERVICE_UNAVAILABLE),
	
	/** The exception com forbidden. */
	EXCEPTION_COM_FORBIDDEN("T-GENERIC-107",
		HttpStatus.FORBIDDEN),
	
	/** The exception com unauthorized. */
	EXCEPTION_COM_UNAUTHORIZED("T-GENERIC-108",
		HttpStatus.UNAUTHORIZED),
	
	/** The exception com config server error. */
	EXCEPTION_COM_CONFIG_SERVER_ERROR("T-GENERIC-109",
		HttpStatus.SERVICE_UNAVAILABLE),
	
	/** The exception com kafka error. */
	EXCEPTION_COM_KAFKA_ERROR("T-GENERIC-110",
			HttpStatus.INTERNAL_SERVER_ERROR),
	
	/** The exception com log error. */
	EXCEPTION_COM_LOG_ERROR("T-GENERIC-111",
		HttpStatus.INTERNAL_SERVER_ERROR),
	
	/** The exception com conf file error. */
	EXCEPTION_COM_CONF_FILE_ERROR("T-GENERIC-112",
			HttpStatus.INTERNAL_SERVER_ERROR),
	
	/** The exception com invalid attribute format. */
	EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT("F-GENERIC-200",
		HttpStatus.BAD_REQUEST),
	
	/** The exception com invalid pageable parameters. */
	EXCEPTION_COM_INVALID_PAGEABLE_PARAMETERS("F-GENERIC-201",
		HttpStatus.BAD_REQUEST),
	
	/** The exception com insufficient search parameters. */
	EXCEPTION_COM_INSUFFICIENT_SEARCH_PARAMETERS("F-GENERIC-202",
			HttpStatus.NOT_ACCEPTABLE),
	
	/** The exception com element not found. */
	EXCEPTION_COM_ELEMENT_NOT_FOUND("F-GENERIC-203",
		HttpStatus.NOT_FOUND),
	
	/** The exception com existent element. */
	EXCEPTION_COM_EXISTENT_ELEMENT("F-GENERIC-204",
		HttpStatus.CONFLICT),
	
	/** The exception com invalid data format. */
	EXCEPTION_COM_INVALID_DATA_FORMAT("F-GENERIC-205",
		HttpStatus.BAD_REQUEST),
	
	/** The exception com unprocessable entity. */
	EXCEPTION_COM_UNPROCESSABLE_ENTITY("F-GENERIC-206",
		HttpStatus.UNPROCESSABLE_ENTITY);



	/** The code. */
	private final String code;
	
	/** The http code. */
	//private final String message;
	private final Integer httpCode;

	/**
	 * Instantiates a new generic error.
	 *
	 * @param code the code
	 * @param httpCode the http code
	 */
	GenericError(final String code, final HttpStatus httpCode) {
		this.code = code;
		//this.message = message;
		this.httpCode = httpCode.value();
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		//return message;
		return MessageSourceHelper.getMessage(this.name(), null, LocaleContextHolder.getLocale());
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
