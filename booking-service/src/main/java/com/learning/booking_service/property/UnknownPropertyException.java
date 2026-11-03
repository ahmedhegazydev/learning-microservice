package com.learning.booking_service.property;

public class UnknownPropertyException extends RuntimeException {

	public UnknownPropertyException(Long propertyId) {
		super("Property " + propertyId + " does not exist");
	}
}
