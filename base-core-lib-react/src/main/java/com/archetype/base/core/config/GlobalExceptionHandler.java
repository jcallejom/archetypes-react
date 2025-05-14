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

		
		
		
//		var response= e.getBindingResult()
//                .getAllErrors()
//                .stream()
//                .forEach(er -> StandarizedApiExceptionResponse.builder())
//                .map(StandarizedApiExceptionResponse::getDetail)
//                
//                });
//                
//                StandarizedApiExceptionResponse::getDetail)
//                })
//                .collect(Collectors.toList());
//		
//		
////		for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()){
////			response.setDetail(constraintViolation.getRootBeanClass().getName()+":"+constraintViolation.getPropertyPath()+":"+constraintViolation.getMessage());
//	
//		
//				StandarizedApiExceptionResponse.builder()
//				.detail(FUNCTIONAL)
//				.code(FUNCTIONAL)
//				
//				.build();
////		
////		StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse(
////				
////				(GenericError.EXCEPTION_COM_INVALID_DATA_FORMAT.isTechnical()?TECHNICAL:FUNCTIONAL)//type
////				+ ":" +GenericError.class.getName(),
////				GenericError.EXCEPTION_COM_INVALID_DATA_FORMAT.getCode(),//title
////				status.toString(),
//////				GenericError.EXCEPTION_COM_INVALID_DATA_FORMAT.getHttpCode().toString(),//code
////				ex.getMessage(),//detail
////				
////				GenericError.class.getName()
//////				+ ":" + (GenericError.EXCEPTION_COM_INVALID_DATA_FORMAT.isTechnical()?TECHNICAL:FUNCTIONAL) 
////				+ ":" + GenericError.EXCEPTION_COM_INVALID_DATA_FORMAT.name().toLowerCase()
////				);
//		
//		
//		return new ResponseEntity<>(response, HttpStatus.valueOf(GenericError.EXCEPTION_COM_INVALID_DATA_FORMAT.getHttpCode()));
//
////		return handleExceptionInternal(
////			      ex, response, headers, status, request);
	}		
	
	/**
	 * Handle method argument not valid .
	 *
	 * @param ex      this exception is thrown when argument annotated with @Valid failed validation
	 * @param headers the headers
	 * @param status  the status
	 * @param request the request
	 * @return the response entity
	 */
//	@Override
////	@ResponseBody
////	@ExceptionHandler(MethodArgumentNotValidException.class)
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//		
//		StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse(
//				
//				(GenericError.EXCEPTION_COM_INVALID_DATA_FORMAT.isTechnical()?TECHNICAL:FUNCTIONAL)//type
//				+ ":" +GenericError.class.getName(),
//				GenericError.EXCEPTION_COM_INVALID_DATA_FORMAT.getCode(),//title
//				status.toString(),
////				GenericError.EXCEPTION_COM_INVALID_DATA_FORMAT.getHttpCode().toString(),//code
//				ex.getMessage(),//detail
//				
//				GenericError.class.getName()
////				+ ":" + (GenericError.EXCEPTION_COM_INVALID_DATA_FORMAT.isTechnical()?TECHNICAL:FUNCTIONAL) 
//				+ ":" + GenericError.EXCEPTION_COM_INVALID_DATA_FORMAT.name().toLowerCase()
//				);
//		
//		return new ResponseEntity<>(response, HttpStatus.valueOf(GenericError.EXCEPTION_COM_INVALID_DATA_FORMAT.getHttpCode()));
//
////		return handleExceptionInternal(
////			      ex, response, headers, status, request);
//	}		
	
	/**
	 * Handle constraint violation exceptions.
	 *
	 * @param ex this exception reports the result of constraint violations is thrown when controller class annotated with @Validate failed validation
	 * @param request the request
	 * @return the response entity
	 */
//	@ResponseBody
//	@ExceptionHandler(ConstraintViolationException.class)
//	public final ResponseEntity<StandarizedApiExceptionResponse> handleConstraintViolationExceptions(ConstraintViolationException ex, WebRequest request) {
//		
//		StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse(
//				GenericError.class.getName()+ "/" 
//				+ (GenericError.EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT.isTechnical()?"TECHNICAL":"FUNCTIONAL"),
//				GenericError.EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT.getCode(),
//				GenericError.EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT.getHttpCode().toString(),
//				String.format(GenericError.EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT.getMessage(), ex.getMessage()),
//							
//				GenericError.class.getName()+ "/" 
//				+ (GenericError.EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT.isTechnical()?"TECHNICAL":"FUNCTIONAL")+ "/" 
//				+ GenericError.EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT.name()
//				);
//		for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()){
//			response.setDetail(constraintViolation.getRootBeanClass().getName()+":"+constraintViolation.getPropertyPath()+":"+constraintViolation.getMessage());
//		}
//		return new ResponseEntity<>(response, HttpStatus.valueOf(GenericError.EXCEPTION_COM_INVALID_ATTRIBUTE_FORMAT.getHttpCode()));
//
////		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//	}

	
//	@ResponseBody
//	@ExceptionHandler(AuthenticationException.class)
//	public final ResponseEntity<StandarizedApiExceptionResponse> handleAuthenticationException(Exception ex) {
//		
//		StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse(
//				GenericError.class.getName()+ "/" 
//				+ (GenericError.EXCEPTION_COM_SECURITY_ERROR.isTechnical()?"TECHNICAL":"FUNCTIONAL"),
//				GenericError.EXCEPTION_COM_SECURITY_ERROR.getCode(),
//				GenericError.EXCEPTION_COM_SECURITY_ERROR.getHttpCode().toString(),
//				String.format(GenericError.EXCEPTION_COM_SECURITY_ERROR.getMessage(), ex.getMessage()),
//							
//				GenericError.class.getName()+ "/" 
//				+ (GenericError.EXCEPTION_COM_SECURITY_ERROR.isTechnical()?"TECHNICAL":"FUNCTIONAL")+ "/" 
//				+ GenericError.EXCEPTION_COM_SECURITY_ERROR.name()
//				);
//		
////		return new ResponseEntity<>(response, HttpStatus.valueOf(GenericError.EXCEPTION_COM_SECURITY_ERROR.getHttpCode()));
//		return  ResponseEntity.status(HttpStatus.valueOf(GenericError.EXCEPTION_COM_SECURITY_ERROR.getHttpCode())).body (response);
//	}	
	
//	@ResponseBody
//	protected ResponseEntity<ResponseInfo> globalError (final Exception exception,
//			final HttpStatus status, HttpServletRequest request) {
//
//		ResponseInfo responseInfo=new ResponseInfo();
//		responseInfo.setCode( status.name()+":KO");
//		responseInfo.setDescription("GlobalExceptionHandler 0:");
//		responseInfo.setUriRequest( request.getRequestURI());
//		responseInfo.getAdditionalInformation().put("HttpStatus", status.name());
//		responseInfo.getAdditionalInformation().put("timestamp", new Date().toString());
//			
//		return new ResponseEntity<>(responseInfo, status);
//		
//	
//	}
//	/**
//	 * 
//	 * @param request
//	 * @param ex this exception reports the execution errors 
//	 * @return 500 Internal Server Error.
//	 */
//	@ExceptionHandler(RuntimeException.class)
//	public ResponseEntity<ResponseInfo> handleRuntimeException(HttpServletRequest request, final RuntimeException ex) {
//		ResponseInfo responseInfo=new ResponseInfo();
//		responseInfo.setCode(HttpStatus.INTERNAL_SERVER_ERROR.name()+":KO");
//		responseInfo.setDescription(String.format("Error runtime en el sistema: %s", ex.getMessage()));
//		responseInfo.setUriRequest(request.getRequestURI());
//		responseInfo.getAdditionalInformation().put("HttpStatus", HttpStatus.INTERNAL_SERVER_ERROR.name());
//		responseInfo.getAdditionalInformation().put("timestamp", new Date().toString());
//		return new ResponseEntity<>(responseInfo, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	/**
//	 * 
//	 * @param ex this exception reports problems with connection or connection ports
//	 * @param request
//	 * @return 500 Internal Server Error.
//	 */
//	@ExceptionHandler(ConnectException.class)
//	@ResponseBody
//	public ResponseEntity<ResponseInfo> connectionRefusedException(ConnectException ex, HttpServletRequest request) {
//		ResponseInfo responseInfo=new ResponseInfo();
//		responseInfo.setCode(HttpStatus.INTERNAL_SERVER_ERROR.name()+":KO");
//		responseInfo.setDescription(String.format("Error en conexion: %s", ex.getMessage()));
//		responseInfo.setUriRequest(request.getRequestURI());
//		responseInfo.getAdditionalInformation().put("HttpStatus", HttpStatus.INTERNAL_SERVER_ERROR.name());
//		responseInfo.getAdditionalInformation().put("timestamp", new Date().toString());
//		return new ResponseEntity<>(responseInfo,  HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	/**
//	 * 
//	 * @param ex this exception reports spring,hibernate,JPA problems with pesimistic lock
//	 * @param request
//	 * @return 400 Bad Request.
//	 */
//	@ExceptionHandler(ObjectOptimisticLockingFailureException.class)
//	@ResponseBody
//	public ResponseEntity<ResponseInfo> objectOptimisticLockingFailureExceptionException(
//			ObjectOptimisticLockingFailureException ex, HttpServletRequest request) {
//		ResponseInfo responseInfo=new ResponseInfo();
//		responseInfo.setCode(HttpStatus.BAD_REQUEST.name()+":KO");
//		responseInfo.setDescription(String.format("Error.El elemento que está actualizando has sido modificado previamente. Recarguelo y reintentelo.: %s", ex.getMessage()));
//		responseInfo.setUriRequest(request.getRequestURI());
//		responseInfo.getAdditionalInformation().put("HttpStatus", HttpStatus.BAD_REQUEST.name());
//		responseInfo.getAdditionalInformation().put("timestamp", new Date().toString());
//		return new ResponseEntity<>(responseInfo, HttpStatus.BAD_REQUEST);
//
//	}

}