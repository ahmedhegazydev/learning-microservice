package com.learning.property_service.property;

import java.math.BigDecimal;

// The API shape, kept separate from the entity so a database change
// doesn't change what clients receive
public record PropertyResponse(Long id, String title, String city, BigDecimal price) {

	static PropertyResponse from(Property property) {
		return new PropertyResponse(property.getId(), property.getTitle(), property.getCity(),
				property.getPrice());
	}
}
