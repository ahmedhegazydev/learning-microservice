package com.learning.booking_service.booking;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	private final BookingService service;

	public BookingController(BookingService service) {
		this.service = service;
	}

	@GetMapping
	public List<BookingResponse> findByProperty(@RequestParam Long propertyId) {
		return service.findByProperty(propertyId);
	}

	@GetMapping("/{id}")
	public BookingResponse findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public ResponseEntity<BookingResponse> create(@Valid @RequestBody CreateBookingRequest request) {
		BookingResponse created = service.create(request);
		return ResponseEntity.created(URI.create("/bookings/" + created.id())).body(created);
	}
}
