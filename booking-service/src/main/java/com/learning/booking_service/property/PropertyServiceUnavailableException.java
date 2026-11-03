package com.learning.booking_service.property;

public class PropertyServiceUnavailableException extends RuntimeException {

	public PropertyServiceUnavailableException(Throwable cause) {
		super("Property service is unavailable, try again later", cause);
	}
}
