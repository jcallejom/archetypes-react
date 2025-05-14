/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.utils;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.archetype.base.core.exception.FunctionalException;
import com.archetype.base.core.exception.model.GenericError;

import jakarta.annotation.PostConstruct;

// TODO: Auto-generated Javadoc
/**
 * Este componente se encarga de extraer el mensaje del fichero .properties
 * @author jcallejo
 *
 */
@Component
public class MessageSourceHelper {

	/** The injected message source. */

	@Autowired
	private MessageSource injectedMessageSource;

	/** The message source. */
	private static MessageSource messageSource;
	
	/** The log. */
	static Logger log=LoggerFactory.getLogger(MessageSourceHelper.class);



/**
 * Gets the message.
 *
 * @param messageKey the message key
 * @param arguments the arguments
 * @param locale the locale
 * @return the message
 */
public static String getMessage(final String messageKey, final Object[] arguments, final Locale locale) {
		log.debug("Estado: messageSource "+ messageSource +"locale: "+locale +":"+LocaleContextHolder.getLocale());
		return messageSource != null ? messageSource.getMessage(messageKey, arguments, locale) : "";
	}
	/**
	 * Post construct.
	 */
	@PostConstruct
	public void postConstruct() {
		messageSource = injectedMessageSource;
	}


}
