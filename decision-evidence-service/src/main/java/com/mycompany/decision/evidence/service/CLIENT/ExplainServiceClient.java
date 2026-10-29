
package com.mycompany.decision.evidence.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class ExplainServiceClient {

    private final RestTemplate restTemplate;

    private static final String EXPLAIN_SERVICE_URL =
            "http://localhost:8086/api/explain";

    public ExplainServiceClient() {
        this.restTemplate = new RestTemplate();
    }

    public Map<String, Object> getExplanation(
            Map<String, Object> request) {

        ResponseEntity<Map> response =
                restTemplate.postForEntity(
                        EXPLAIN_SERVICE_URL,
                        request,
                        Map.class);

        return response.getBody();
    }
}
