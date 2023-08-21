package com.lcwd.user.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lcwd.user.service.payload.APIResponse;


@ControllerAdvice
public class GlobalExceptionHandler {

	public GlobalExceptionHandler() {
		// TODO Auto-generated constructor stub
	}
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException ex)
	{
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(ex.getMessage()));
				//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)/*.contentType(MediaType.APPLICATION_JSON)*/.body(new EmployeeResponse(ex.getMessage()));
	}

}
