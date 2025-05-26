/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.config;



import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.archetype.base.core.exception.model.GenericError;
import com.archetype.base.core.exception.model.StandarizedApiExceptionResponse;

import jakarta.validation.ConstraintViolation;


// TODO: Auto-generated Javadoc
/**
 * The Class GlobalExceptionHandler.
 */

//@RestControllerAdvice(basePackages = { "com.archetype" })
//@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {//extends ResponseEntityExceptionHandler {

	/** The technical. */
	private final String TECHNICAL="Technical";
	
	/** The functional. */
	private final String FUNCTIONAL="Functional";
	
	@ExceptionHandler(WebExchangeBindException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(WebExchangeBindException e) {
		
		
		var errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
//		return ResponseEntity.badRequest().body(errors);
		
				StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse(
				GenericError.class.getName()+ "/" 
				+ (GenericError.EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT.isTechnical()?"TECHNICAL":"FUNCTIONAL"),
				GenericError.EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT.getCode(),
				GenericError.EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT.getHttpCode().toString(),
				String.format(GenericError.EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT.getMessage(), e.getMessage()),
							
				GenericError.class.getName()+ "/" 
				+ (GenericError.EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT.isTechnical()?"TECHNICAL":"FUNCTIONAL")+ "/" 
				+ GenericError.EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT.name()
				);
		for (var error : errors){
			response.setDetail(error.toString());
		}
		return new ResponseEntity<>(response, HttpStatus.valueOf(GenericError.EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT.getHttpCode()));
	}
		
		
		


}