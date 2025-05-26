/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.config;

import java.net.UnknownHostException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono; // Importante para tipos reactivos

import com.archetype.base.core.exception.FunctionalException;
import com.archetype.base.core.exception.TechnicalRuntimeException;
import com.archetype.base.core.exception.model.GenericError;
import com.archetype.base.core.exception.model.StandarizedApiExceptionResponse;


/**
 * The Class ApiExceptionHandler.
 *
 * @author jcallejo
 * Standard http communication have five levels of response
 * codes 100-level (Informational) — Server acknowledges a request, it
 * mean that request was received and understood, it is transient
 * response , alert client for awaiting response 200-level (Success) —
 * Server completed the request as expected 300-level (Redirection) —
 * Client needs to perform further actions to complete the request
 * 400-level (Client error) — Client sent an invalid request 500-level
 * (Server error) — Server failed to fulfill a valid request due to an
 * error with server.
 * The goal of handler exception is provide to customer with appropriate
 * code and additional comprehensible information to help troubleshoot
 * the problem. The message portion of the body should be present as
 * user interface, event if customer send an Accept-Language header (en
 * or french ie) we should translate the title part to customer language
 * if we support internationalization, detail is intended for developer
 * of clients, so the translation is not necessary. If more than one
 * error is need to report , we can response a list of errors.
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * Handle unknown host exception.
     *
     * @param ex the ex
     * @return the Mono of response entity
     */
    @ExceptionHandler(UnknownHostException.class)
    public Mono<ResponseEntity<StandarizedApiExceptionResponse>>
            handleUnknownHostException(final UnknownHostException ex) {
        StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse(
                GenericError.class.getName()+ "/"
                + (GenericError.EXCEPTION_COM_NETWORK_ERROR.isTechnical()?"TECNICAL":"FUNCIONAL"),
                GenericError.EXCEPTION_COM_NETWORK_ERROR.getCode(),
                GenericError.EXCEPTION_COM_NETWORK_ERROR.getHttpCode().toString(),
                GenericError.EXCEPTION_COM_NETWORK_ERROR.getCode()+":"+ex.getMessage(),
                GenericError.class.getName()+ "/"
                + (GenericError.EXCEPTION_COM_NETWORK_ERROR.isTechnical()?"TECNICAL":"FUNCIONAL")+ "/"
                + GenericError.EXCEPTION_COM_NETWORK_ERROR.name()
                );
        // En WebFlux, encapsulamos la ResponseEntity en un Mono
        return Mono.just(new ResponseEntity<>(response, HttpStatus.valueOf(GenericError.EXCEPTION_COM_NETWORK_ERROR.getHttpCode())));
    }

    /**
     * Handle functional exception.
     *
     * @param ex the ex
     * @return the Mono of response entity
     */
    @ExceptionHandler(FunctionalException.class)
    public Mono<ResponseEntity<StandarizedApiExceptionResponse>>
                handleFunctionalException(final FunctionalException ex) {
                StandarizedApiExceptionResponse response
                = new StandarizedApiExceptionResponse(
                    ex.getClass().getName()+ "/FUNCTIONAL",
                    ex.getErrorCode(),
                    ex.getHttpCode().toString(),
                    ex.getErrorCode()+":"+ ex.getMessage(),
                    ex.getClass().getName()+ "/FUNCTIONAL/"+ ex.getErrorCode());

                return Mono.just(new ResponseEntity<>(response, HttpStatus.valueOf(ex.getHttpCode())));
    }

    /**
     * Handle technical runtime exception.
     *
     * @param ex the ex
     * @return the Mono of response entity
     */
    @ExceptionHandler(TechnicalRuntimeException.class)
    public Mono<ResponseEntity<StandarizedApiExceptionResponse>>
                handleTechnicalRuntimeException(final TechnicalRuntimeException ex) {
                StandarizedApiExceptionResponse response
                = new StandarizedApiExceptionResponse(
                    ex.getClass().getName()+ "/TECHNICAL",
                    ex.getErrorCode(),
                    ex.getHttpCode().toString(),
                    ex.getErrorCode()+":"+ ex.getMessage(),
                    ex.getClass().getName()+ "/TECHNICAL/"+ ex.getErrorCode());

                return Mono.just(new ResponseEntity<>(response, HttpStatus.valueOf(ex.getHttpCode())));
    }
}