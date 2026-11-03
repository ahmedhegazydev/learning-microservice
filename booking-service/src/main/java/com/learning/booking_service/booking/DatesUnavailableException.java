package com.learning.booking_service.booking;

public class DatesUnavailableException extends RuntimeException {

	public DatesUnavailableException(Long propertyId) {
		super("Property " + propertyId + " is already booked on some of these dates");
	}
}
