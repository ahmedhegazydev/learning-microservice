package com.learning.property_service.property;

import java.math.BigDecimal;

public record Property(Long id, String title, String city, BigDecimal price) {
}
