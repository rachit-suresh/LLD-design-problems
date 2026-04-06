package org.example;

public class RequestEntity {
	private final String clientId;
	private final String endpoint;
	private final String payload;

	public RequestEntity(String clientId, String endpoint, String payload) {
		if (clientId == null || clientId.isBlank()) {
			throw new IllegalArgumentException("clientId must not be blank");
		}
		if (endpoint == null || endpoint.isBlank()) {
			throw new IllegalArgumentException("endpoint must not be blank");
		}
		this.clientId = clientId;
		this.endpoint = endpoint;
		this.payload = payload == null ? "" : payload;
	}

	public String getClientId() {
		return clientId;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public String getPayload() {
		return payload;
	}
}
