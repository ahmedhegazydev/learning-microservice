package com.learning.property_service.property;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

// In memory until Stage 2 swaps in a database; Tomcat serves requests on
// several threads at once, so both the map and the id counter are thread-safe
@Repository
public class PropertyRepository {

	private final Map<Long, Property> properties = new ConcurrentHashMap<>();
	private final AtomicLong nextId = new AtomicLong(1);

	public Property save(String title, String city, BigDecimal price) {
		long id = nextId.getAndIncrement();
		Property property = new Property(id, title, city, price);
		properties.put(id, property);
		return property;
	}

	public List<Property> findAll() {
		return List.copyOf(properties.values());
	}

	public Optional<Property> findById(Long id) {
		return Optional.ofNullable(properties.get(id));
	}
}
