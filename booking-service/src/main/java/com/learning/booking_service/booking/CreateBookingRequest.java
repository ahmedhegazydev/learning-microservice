package com.learning.booking_service.booking;

import java.time.LocalDate;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateBookingRequest(
		@NotNull @Positive Long propertyId,
		@NotBlank @Size(max = 120) String guestName,
		@NotNull @FutureOrPresent LocalDate checkIn,
		@NotNull LocalDate checkOut) {

	// Reported as the field "checkOutAfterCheckIn" when it fails
	@AssertTrue(message = "must be after checkIn")
	public boolean isCheckOutAfterCheckIn() {
		return checkIn == null || checkOut == null || checkOut.isAfter(checkIn);
	}
}
