package com.learning.property_service.property;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreatePropertyRequest(
		@NotBlank @Size(max = 120) String title,
		@NotBlank String city,
		@NotNull @Positive BigDecimal price) {
}
