package com.learning.booking_service.booking;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "property_id", nullable = false)
	private Long propertyId;

	@Column(name = "guest_name", nullable = false, length = 120)
	private String guestName;

	@Column(name = "check_in", nullable = false)
	private LocalDate checkIn;

	@Column(name = "check_out", nullable = false)
	private LocalDate checkOut;

	// Set by the database default, so Hibernate never writes it
	@Column(name = "created_at", insertable = false, updatable = false)
	private OffsetDateTime createdAt;

	// Hibernate needs a no-arg constructor to build entities from rows
	protected Booking() {
	}

	public Booking(Long propertyId, String guestName, LocalDate checkIn, LocalDate checkOut) {
		this.propertyId = propertyId;
		this.guestName = guestName;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Long getId() {
		return id;
	}

	public Long getPropertyId() {
		return propertyId;
	}

	public String getGuestName() {
		return guestName;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}
}
