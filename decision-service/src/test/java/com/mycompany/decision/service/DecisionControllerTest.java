package com.mycompany.decision.service;

import com.mycompany.decision.service.dto.DecisionRequest;
import com.mycompany.decision.service.dto.DecisionResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DecisionControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void decisionEndpointShouldReturnMigrationDecision() {

        DecisionRequest request = new DecisionRequest();

        request.setWorkloadId("WL001");
        request.setMrpScore(0.20);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DecisionRequest> entity =
                new HttpEntity<>(request, headers);

        ResponseEntity<DecisionResult> response =
                restTemplate.postForEntity(
                        "http://localhost:" + port + "/api/decision",
                        entity,
                        DecisionResult.class
                );

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());

        assertEquals(
                "WL001",
                response.getBody().getWorkloadId()
        );

        assertEquals(
                0.20,
                response.getBody().getMrpScore()
        );

        assertEquals(
                "MIGRATE",
                response.getBody().getDecision()
        );
    }

    @Test
    void healthEndpointShouldWork() {

        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        "http://localhost:" + port + "/api/decision/health",
                        String.class
                );

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(
                "Decision Service is running",
                response.getBody()
        );
    }
}