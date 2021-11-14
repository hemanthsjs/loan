package com.cg.loanapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
			
		@ExceptionHandler(CustomerException.class)
		public ResponseEntity<ErrorMsg> handleException (CustomerException e){
			ErrorMsg m = new ErrorMsg();
			m.setStatusCode(HttpStatus.BAD_GATEWAY.value());
			m.setErrorMessage(e.getMessage());
			return new ResponseEntity<>(m, HttpStatus.ACCEPTED);
		}
		@ExceptionHandler(Exception.class)
		public ResponseEntity<ErrorMsg> handleGeneralException (CustomerException e){
			ErrorMsg m = new ErrorMsg();
			m.setStatusCode(HttpStatus.BAD_GATEWAY.value());
			m.setErrorMessage(e.getMessage());
			return new ResponseEntity<>(m, HttpStatus.ACCEPTED);
		}

	}


