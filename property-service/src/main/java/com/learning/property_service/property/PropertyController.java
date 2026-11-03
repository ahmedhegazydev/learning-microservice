package com.learning.property_service.property;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/properties")
public class PropertyController {

	private final PropertyService service;

	public PropertyController(PropertyService service) {
		this.service = service;
	}

	@GetMapping
	public List<Property> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Property findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public ResponseEntity<Property> create(@Valid @RequestBody CreatePropertyRequest request) {
		Property created = service.create(request);
		return ResponseEntity.created(URI.create("/properties/" + created.id())).body(created);
	}
}
