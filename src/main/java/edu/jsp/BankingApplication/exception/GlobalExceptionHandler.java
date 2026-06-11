package edu.jsp.BankingApplication.exception;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = ResourcesNotFoundException.class)
	public ResponseEntity<String> handelResourcesNotFoundException(ResourcesNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<LinkedHashMap<String, String>> handelMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {

		LinkedHashMap<String, String> lm = new LinkedHashMap<>();

		List<FieldError> errors = ex.getBindingResult().getFieldErrors();

		for (FieldError err : errors) {

			String fieldName = err.getField();
			String msg = err.getDefaultMessage();
			lm.put(fieldName, msg);
		}

		return new ResponseEntity<LinkedHashMap<String, String>>(lm, HttpStatus.BAD_REQUEST);

	}

}
