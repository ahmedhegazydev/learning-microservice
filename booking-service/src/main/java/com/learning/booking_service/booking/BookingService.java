package com.learning.booking_service.booking;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.booking_service.property.PropertyClient;

@Service
public class BookingService {

	private final BookingRepository repository;
	private final PropertyClient propertyClient;

	public BookingService(BookingRepository repository, PropertyClient propertyClient) {
		this.repository = repository;
		this.propertyClient = propertyClient;
	}

	// Not @Transactional: the call to property-service runs before any database
	// work, so a slow network never holds a connection open. save() runs in its own transaction.
	public BookingResponse create(CreateBookingRequest request) {
		propertyClient.requireExists(request.propertyId());

		Booking booking = new Booking(request.propertyId(), request.guestName().trim(), request.checkIn(),
				request.checkOut());
		try {
			return BookingResponse.from(repository.saveAndFlush(booking));
		}
		catch (DataIntegrityViolationException exception) {
			// The no_overlapping_bookings constraint rejected the dates
			throw new DatesUnavailableException(request.propertyId());
		}
	}

	@Transactional(readOnly = true)
	public List<BookingResponse> findByProperty(Long propertyId) {
		return repository.findByPropertyIdOrderByCheckIn(propertyId).stream().map(BookingResponse::from).toList();
	}

	@Transactional(readOnly = true)
	public BookingResponse findById(Long id) {
		return repository.findById(id)
				.map(BookingResponse::from)
				.orElseThrow(() -> new BookingNotFoundException(id));
	}
}
