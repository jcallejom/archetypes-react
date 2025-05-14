/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.exception.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

// TODO: Auto-generated Javadoc
/**
 * The Class StandarizedApiExceptionResponse.
 *
 * @author jcallejo The effort to standardize rest API error reports is support
 *         by ITEF (Internet Engineering Task Force, open standard organization
 *         that which develop and promotes voluntary internet standards) in RFC
 *         7807 which created a generalized error-handling schema composed by
 *         five parts. 1- type — A URI identifier that categorizes the error
 *         2-title — A brief, human-readable message about the error 3-code —
 *         The unique error code 4-detail — A human-readable explanation of the
 *         error 5-instance — A URI that identifies the specific occurrence of
 *         the error Standarized is optional but have advantage, it is use for
 *         facebook and twitter ie
 *         https://graph.facebook.com/oauth/access_token?
 *         https://api.twitter.com/1.1/statuses/update.json?include_entities=true
 */
@Builder
public class StandarizedApiExceptionResponse {
	
	/** The type. */
	@Schema(description = "The unique uri identifier that categorizes the error", name = "type",
           requiredMode = Schema.RequiredMode.REQUIRED, example = "/errors/authentication/not-authorized")
	private String type = "/errors/uncategorized";
	
	/** The title. */
	@Schema(description = "A brief, human-readable message about the error", name = "title",
           requiredMode = Schema.RequiredMode.REQUIRED, example = "The user does not have autorization")
    private String title;
	
	/** The code. */
	@Schema(description = "The unique error code", name = "code",
    		 requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "401")
    private String code;
	
	/** The detail. */
	@Schema(description = "A human-readable explanation of the error", name = "detail",
           requiredMode = Schema.RequiredMode.REQUIRED,
           example = "The user does not have the propertly persmissions to acces the "
                   + "resource, please contact with ass ONCE: https://example.once.es")
    private String detail;
	
	/** The instance. */
	@Schema(description = "A URI that identifies the specific occurrence of the error", name = "detail",
           requiredMode = Schema.RequiredMode.REQUIRED, example = "/errors/authentication/not-authorized/01")
	private String instance = "/errors/uncategorized/pdcf";

	/**
	 * Instantiates a new standarized api exception response.
	 *
	 * @param type the type
	 * @param title the title
	 * @param code the code
	 * @param detail the detail
	 * @param instance the instance
	 */
	public StandarizedApiExceptionResponse(final String type, final String title,
			final String code, final String detail, final String instance) {
		super();
		this.type = type;
		this.title = title;
		this.code = code;
		this.detail = detail;
		this.instance = instance;
	}

	/**
	 * Instantiates a new standarized api exception response.
	 *
	 * @param title the title
	 * @param code the code
	 * @param detail the detail
	 * @param instance the instance
	 */
	public StandarizedApiExceptionResponse(final String title, final String code, final String detail,
			final String instance) {
        super();
        this.title = title;
        this.code = code;
        this.detail = detail;
		this.instance = instance;
	}
	
	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	@JsonGetter("code")
    public String getCode() {
        return code;
    }

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(final String code) {
        this.code = code;
    }
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	@JsonGetter("type")
    public String getType() {
        return type;
    }

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(final String type) {
        this.type = type;
    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(final String title) {
        this.title = title;
    }


    /**
     * Gets the detail.
     *
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

	/**
	 * Sets the detail.
	 *
	 * @param detail the new detail
	 */
	public void setDetail(final String detail) {
        this.detail = detail;
    }

    /**
     * Gets the single instance of StandarizedApiExceptionResponse.
     *
     * @return single instance of StandarizedApiExceptionResponse
     */
    public String getInstance() {
        return instance;
    }

	/**
	 * Sets the instance.
	 *
	 * @param instance the new instance
	 */
	public void setInstance(final String instance) {
        this.instance = instance;
    }
	
//	@Schema(description = "The unique error status", name = "status",
//   		 requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "401")
//	@JsonGetter("status")
//    public String getStatus() {
//        return code;
//    }
//
//	public void setStatus(final String status) {
//        this.code = status;
//    }
}
