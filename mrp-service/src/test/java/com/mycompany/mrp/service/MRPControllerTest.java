package com.mycompany.mrp.service;

import com.mycompany.mrp.service.dto.MRPRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class MRPControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void healthEndpointShouldReturnRunning() {

        String url = "http://localhost:" + port + "/api/mrp/health";

        ResponseEntity<String> response =
                restTemplate.getForEntity(url, String.class);

        assertEquals(200, response.getStatusCode().value());

        assertEquals(
                "MRP Service is running",
                response.getBody()
        );
    }

    @Test
    void mrpEndpointShouldCalculateScore() {

        String url = "http://localhost:" + port + "/api/mrp";

        MRPRequest request = new MRPRequest();

        request.setWorkloadId("WL001");
        request.setDgs(0.80);
        request.setAss(0.60);
        request.setDci(0.70);
        request.setCvs(0.40);
        request.setMoc(0.90);

        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        url,
                        request,
                        String.class
                );

        assertEquals(200, response.getStatusCode().value());

        assertNotNull(response.getBody());

        assertTrue(response.getBody().contains("WL001"));

        assertTrue(response.getBody().contains("0.7"));

        assertTrue(response.getBody().contains("HIGH"));
    }
}