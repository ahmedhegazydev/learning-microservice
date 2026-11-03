package com.learning.booking_service.booking;

public class BookingNotFoundException extends RuntimeException {

	public BookingNotFoundException(Long id) {
		super("Booking " + id + " not found");
	}
}
