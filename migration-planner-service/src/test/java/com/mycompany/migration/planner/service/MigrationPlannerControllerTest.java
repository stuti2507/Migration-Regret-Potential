package com.mycompany.migration.planner.service;

import com.mycompany.migration.planner.service.dto.MigrationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MigrationPlannerControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void healthEndpointShouldWork() {

        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        "http://localhost:" + port + "/api/migration/health",
                        String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(
                "Migration Planner Service is running",
                response.getBody());
    }

    @Test
    void migrateDecisionShouldCreatePlan() {

        MigrationRequest request = new MigrationRequest();

        request.setWorkloadId("WL001");
        request.setSourceCloud("AWS");
        request.setTargetCloud("Azure");
        request.setMrpScore(0.15);
        request.setDecision("MIGRATE");

        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        "http://localhost:" + port + "/api/migration",
                        request,
                        String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertTrue(response.getBody().contains("MIGRATE"));
        assertTrue(response.getBody().contains("HIGH"));
        assertTrue(response.getBody().contains("PLANNED"));
    }

    @Test
    void reviewDecisionShouldCreateReviewPlan() {

        MigrationRequest request = new MigrationRequest();

        request.setWorkloadId("WL002");
        request.setSourceCloud("Azure");
        request.setTargetCloud("GCP");
        request.setMrpScore(0.50);
        request.setDecision("REVIEW");

        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        "http://localhost:" + port + "/api/migration",
                        request,
                        String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertTrue(response.getBody().contains("MANUAL_REVIEW"));
        assertTrue(response.getBody().contains("MEDIUM"));
        assertTrue(response.getBody().contains("PENDING_REVIEW"));
    }

    @Test
    void noMigrationDecisionShouldCreateNoMigrationPlan() {

        MigrationRequest request = new MigrationRequest();

        request.setWorkloadId("WL003");
        request.setSourceCloud("GCP");
        request.setTargetCloud("AWS");
        request.setMrpScore(0.80);
        request.setDecision("DO_NOT_MIGRATE");

        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        "http://localhost:" + port + "/api/migration",
                        request,
                        String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertTrue(response.getBody().contains("NO_MIGRATION"));
        assertTrue(response.getBody().contains("LOW"));
        assertTrue(response.getBody().contains("CANCELLED"));
    }
}