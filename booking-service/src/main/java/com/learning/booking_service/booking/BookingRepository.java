package com.learning.booking_service.booking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	// Spring Data builds the query from the method name
	List<Booking> findByPropertyIdOrderByCheckIn(Long propertyId);
}
