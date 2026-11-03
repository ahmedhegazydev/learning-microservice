package com.learning.booking_service.booking;

import java.time.LocalDate;

// The API shape, kept separate from the entity so a database change
// doesn't change what clients receive
public record BookingResponse(Long id, Long propertyId, String guestName, LocalDate checkIn, LocalDate checkOut) {

	static BookingResponse from(Booking booking) {
		return new BookingResponse(booking.getId(), booking.getPropertyId(), booking.getGuestName(),
				booking.getCheckIn(), booking.getCheckOut());
	}
}
