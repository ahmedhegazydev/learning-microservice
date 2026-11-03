package com.learning.property_service.property;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "properties")
public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 120)
	private String title;

	@Column(nullable = false, length = 80)
	private String city;

	@Column(nullable = false, precision = 14, scale = 2)
	private BigDecimal price;

	// Set by the database default, so Hibernate never writes it
	@Column(name = "created_at", insertable = false, updatable = false)
	private OffsetDateTime createdAt;

	// Hibernate needs a no-arg constructor to build entities from rows
	protected Property() {
	}

	public Property(String title, String city, BigDecimal price) {
		this.title = title;
		this.city = city;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getCity() {
		return city;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}
}
