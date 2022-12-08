package com.cgs.loyalty.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.cgs.loyalty.exception.CustomerNotExistException;
import com.cgs.loyalty.exception.IdAlreadyExistException;
import com.cgs.loyalty.exception.InvalidChannelOfRegistrationException;
import com.cgs.loyalty.exception.InvalidCustomerTypeException;
import com.cgs.loyalty.exception.InvalidDobException;
import com.cgs.loyalty.exception.InvalidEmailException;
import com.cgs.loyalty.exception.InvalidIdException;
import com.cgs.loyalty.exception.InvalidMobileNoException;
import com.cgs.loyalty.exception.InvalidNameException;
import com.cgs.loyalty.exception.InvalidRatingException;
import com.cgs.loyalty.exception.ListIsEmptyException;

@ControllerAdvice
public class CustomerLoyaltyAdvice extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		return super.handleMissingPathVariable(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNullPointerException(NullPointerException nullPointerException) {

		return new ResponseEntity<String>("Input id Null ", HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(CustomerNotExistException.class)
	public ResponseEntity<String> handleCustomerNotExistException(CustomerNotExistException customerNotExistException) {

		return new ResponseEntity<String>("Given employee id not exist in database", HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(ListIsEmptyException.class)
	public ResponseEntity<String> handleListIsEmptyException(ListIsEmptyException listIsEmptyException) {

		return new ResponseEntity<String>("data base is empty there is no any data present", HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(InvalidIdException.class)
	public ResponseEntity<String> handleEmptyInput(InvalidIdException invalidIdException) {

		return new ResponseEntity<String>("Input id field are empty or not valid ", HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(InvalidEmailException.class)
	public ResponseEntity<String> handleEmptyInput(InvalidEmailException invalidEmailxception) {

		return new ResponseEntity<String>("Input email field are empty or not in the formate ", HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(InvalidDobException.class)
	public ResponseEntity<String> handleEmptyInput(InvalidDobException invalidDobException) {

		return new ResponseEntity<String>("Input date of birth field are empty or not in the formate ",
				HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(InvalidMobileNoException.class)
	public ResponseEntity<String> handleEmptyInput(InvalidMobileNoException invalidMobileNoException) {

		return new ResponseEntity<String>("Input mobile number field are empty or not in the formate ",
				HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(InvalidRatingException.class)
	public ResponseEntity<String> handleEmptyInput(InvalidRatingException invalidRatingException) {

		return new ResponseEntity<String>("Input rating field are empty or not in the rang 1-5 ",
				HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(InvalidNameException.class)
	public ResponseEntity<String> handleEmptyInput(InvalidNameException invalidNameException) {

		return new ResponseEntity<String>("Input Name field is empty  ", HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(InvalidCustomerTypeException.class)
	public ResponseEntity<String> handleEmptyInput(InvalidCustomerTypeException invalidCustomerTypeException) {

		return new ResponseEntity<String>("Input Customer type field is empty  ", HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(InvalidChannelOfRegistrationException.class)
	public ResponseEntity<String> handleEmptyInput(
			InvalidChannelOfRegistrationException invalidChannelOfRegistrationException) {

		return new ResponseEntity<String>("Input ChannelOfRegistration type field is empty  ", HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(IdAlreadyExistException.class)
	public ResponseEntity<String> handleEmptyInput(IdAlreadyExistException idAlreadyExistException) {

		return new ResponseEntity<String>("This id already present in data base", HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception exception) {
		
		return new ResponseEntity<String>("Something went rwong plese try again", HttpStatus.BAD_REQUEST);
		
	}
	
	

}
