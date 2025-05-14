/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.model.response;
import com.archetype.base.core.model.request.BaseRequest;


// TODO: Auto-generated Javadoc
/**
 * The Class BaseResponse.
 *
 * @param <T> the generic type
 */
public abstract class BaseResponse<T> extends BaseRequest {

	/** The menssage. */
	private String menssage;
	
	/** The id. */
	protected T id;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public T getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(final T id) {
		this.id = id;
	}

	/**
	 * Gets the menssage.
	 *
	 * @return the menssage
	 */
	public String getMenssage() {
		return menssage;
	}

	/**
	 * Sets the menssage.
	 *
	 * @param menssage the new menssage
	 */
	public void setMenssage(final String menssage) {
		this.menssage = menssage;
	}

}
