package com.learning.property_service.property;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PropertyService {

	private final PropertyRepository repository;

	public PropertyService(PropertyRepository repository) {
		this.repository = repository;
	}

	public Property create(CreatePropertyRequest request) {
		return repository.save(request.title().trim(), request.city().trim(), request.price());
	}

	public List<Property> findAll() {
		return repository.findAll();
	}

	public Property findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new PropertyNotFoundException(id));
	}
}
