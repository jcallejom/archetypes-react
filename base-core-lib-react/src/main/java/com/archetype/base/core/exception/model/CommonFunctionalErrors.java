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
 * The Enum CommonFunctionalErrors.
 */
public enum CommonFunctionalErrors implements BaseError {

	/** The exception com null field. */
	EXCEPTION_COM_NULL_FIELD("F-100", HttpStatus.BAD_REQUEST),
	
	/** The exception com record already exists. */
	EXCEPTION_COM_RECORD_ALREADY_EXISTS("F-101", HttpStatus.BAD_REQUEST),
	
	/** The exception com record not found. */
	EXCEPTION_COM_RECORD_NOT_FOUND("F-102",  HttpStatus.BAD_REQUEST),
	
	/** The exception com illegal argument. */
	EXCEPTION_COM_ILLEGAL_ARGUMENT("F-103",  HttpStatus.BAD_REQUEST),
	
	/** The exception com database error. */
	EXCEPTION_COM_DATABASE_ERROR("T-100", HttpStatus.INTERNAL_SERVER_ERROR),
	
	/** The exception com ldap error. */
	EXCEPTION_COM_LDAP_ERROR("T-101",  HttpStatus.INTERNAL_SERVER_ERROR),
	
	/** The exception com search 001. */
	EXCEPTION_COM_SEARCH_001("F-104",  HttpStatus.BAD_REQUEST),
	
	/** The exception com search 002. */
	EXCEPTION_COM_SEARCH_002("F-105",  HttpStatus.BAD_REQUEST),
	
	/** The exception com seg user 001. */
	EXCEPTION_COM_SEG_USER_001("F-106",  HttpStatus.BAD_REQUEST),
	
	/** The exception com seg user 002. */
	EXCEPTION_COM_SEG_USER_002("F-106", HttpStatus.BAD_REQUEST);

    /** The code. */
    private final String code;
  
  /** The http code. */
  //  private final String message;
    private final Integer httpCode;

	/**
	 * Instantiates a new common functional errors.
	 *
	 * @param code the code
	 * @param httpCode the http code
	 */
	CommonFunctionalErrors(final String code, final HttpStatus httpCode) {
        this.code = code;
      //  this.message = message;
        this.httpCode = httpCode.value();
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    @Override
    public String getCode() {
        return code;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    @Override
    public String getMessage() {
       // return message;
        return MessageSourceHelper.getMessage(this.name(), null, LocaleContextHolder.getLocale());
    }

    /**
     * Gets the http code.
     *
     * @return the http code
     */
    @Override
    public Integer getHttpCode() {
        return httpCode;
    }

}

