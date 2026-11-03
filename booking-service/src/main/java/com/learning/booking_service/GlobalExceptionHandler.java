package com.learning.booking_service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.learning.booking_service.booking.BookingNotFoundException;
import com.learning.booking_service.booking.DatesUnavailableException;
import com.learning.booking_service.property.PropertyServiceUnavailableException;
import com.learning.booking_service.property.UnknownPropertyException;

// One place that turns exceptions into RFC 9457 problem responses, so every
// endpoint returns the same error shape and no internals reach the client
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ProblemDetail handleValidation(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new LinkedHashMap<>();
		for (FieldError error : exception.getBindingResult().getFieldErrors()) {
			errors.putIfAbsent(error.getField(), error.getDefaultMessage());
		}

		ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
				"Request has invalid fields");
		problem.setTitle("Validation failed");
		problem.setProperty("errors", errors);
		return problem;
	}

	@ExceptionHandler(BookingNotFoundException.class)
	public ProblemDetail handleNotFound(BookingNotFoundException exception) {
		return problem(HttpStatus.NOT_FOUND, "Booking not found", exception);
	}

	// 422, not 404: the URL is fine, but the body points at a property that doesn't exist
	@ExceptionHandler(UnknownPropertyException.class)
	public ProblemDetail handleUnknownProperty(UnknownPropertyException exception) {
		return problem(HttpStatus.UNPROCESSABLE_CONTENT, "Unknown property", exception);
	}

	@ExceptionHandler(DatesUnavailableException.class)
	public ProblemDetail handleDatesUnavailable(DatesUnavailableException exception) {
		return problem(HttpStatus.CONFLICT, "Dates unavailable", exception);
	}

	@ExceptionHandler(PropertyServiceUnavailableException.class)
	public ProblemDetail handlePropertyServiceDown(PropertyServiceUnavailableException exception) {
		return problem(HttpStatus.SERVICE_UNAVAILABLE, "Dependency unavailable", exception);
	}

	private static ProblemDetail problem(HttpStatus status, String title, RuntimeException exception) {
		ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, exception.getMessage());
		problem.setTitle(title);
		return problem;
	}
}
