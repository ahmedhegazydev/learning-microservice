package com.learning.property_service.property;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PropertyService {

	private final PropertyRepository repository;

	public PropertyService(PropertyRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public PropertyResponse create(CreatePropertyRequest request) {
		Property property = new Property(request.title().trim(), request.city().trim(), request.price());
		return PropertyResponse.from(repository.save(property));
	}

	@Transactional(readOnly = true)
	public List<PropertyResponse> findAll() {
		return repository.findAll().stream().map(PropertyResponse::from).toList();
	}

	@Transactional(readOnly = true)
	public PropertyResponse findById(Long id) {
		return repository.findById(id)
				.map(PropertyResponse::from)
				.orElseThrow(() -> new PropertyNotFoundException(id));
	}
}
