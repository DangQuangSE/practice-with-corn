package dev.practice.integrations.http;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ExternalCatalogClient {
    private final RestClient client;

    public ExternalCatalogClient(RestClient externalRestClient) {
        this.client = externalRestClient;
    }

    public ExternalItem fetch(String externalId) {
        // INT-01: map only required fields; translate provider error/timeout and bound retry policy.
        throw new UnsupportedOperationException("TODO: implement external REST mapping");
    }

    public record ExternalItem(String id, String displayName) {
    }
}
