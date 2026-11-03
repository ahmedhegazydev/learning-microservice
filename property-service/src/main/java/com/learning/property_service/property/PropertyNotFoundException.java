package com.learning.property_service.property;

public class PropertyNotFoundException extends RuntimeException {

	public PropertyNotFoundException(Long id) {
		super("Property " + id + " not found");
	}
}
