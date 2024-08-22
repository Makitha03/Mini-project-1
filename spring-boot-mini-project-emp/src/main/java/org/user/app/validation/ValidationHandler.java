package org.user.app.validation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationHandler  extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		Map<String, String> response=new HashMap<String, String>();
		
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField(); 
			String message=error.getDefaultMessage();
			
			response.put(fieldName, message);
		});
		
		return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NullPointerException.class)
	public  ResponseEntity<Map<String, String>> nullPointerExceptionexceptionHandler(NullPointerException exception)
	{
		Map<String, String> response=new HashMap<String, String>();
		response.put("message", exception.getMessage().toString());
		return new ResponseEntity<Map<String, String>>(response,HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(ArithmeticException.class)
	public  ResponseEntity<Map<String, String>> exceptionHandler(ArithmeticException exception)
	{
		Map<String, String> response=new HashMap<String, String>();
		response.put("message", exception.getMessage().toString());
		return new ResponseEntity<Map<String, String>>(response,HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public  ResponseEntity<Map<String, String>> UserIdNotFoundExceptionExceptionHandler(UserNotFoundException exception)
	{
		Map<String, String> response=new HashMap<String, String>();
		response.put("message", exception.getMessage().toString());
		response.put("date", new Date().toString());
		return new ResponseEntity<Map<String, String>>(response,HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler(Exception.class)
	public  ResponseEntity<Map<String, String>> exceptionHandler(Exception exception)
	{
		Map<String, String> response=new HashMap<String, String>();
		response.put("message", exception.getMessage().toString());
		return new ResponseEntity<Map<String, String>>(response,HttpStatus.NOT_FOUND);
	}
		

}