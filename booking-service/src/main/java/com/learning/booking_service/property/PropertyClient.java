package com.learning.booking_service.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

// The only place that talks to property-service. The rest of booking-service
// sees exceptions of its own, not HTTP details.
@Component
public class PropertyClient {

	private final RestClient restClient;

	public PropertyClient(RestClient.Builder builder, @Value("${property-service.url}") String baseUrl) {
		this.restClient = builder.baseUrl(baseUrl).build();
	}

	public void requireExists(Long propertyId) {
		try {
			restClient.get().uri("/properties/{id}", propertyId).retrieve().toBodilessEntity();
		}
		catch (HttpClientErrorException exception) {
			if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new UnknownPropertyException(propertyId);
			}
			throw exception;
		}
		catch (ResourceAccessException exception) {
			// Connection refused or timed out: property-service is down or unreachable
			throw new PropertyServiceUnavailableException(exception);
		}
	}
}
