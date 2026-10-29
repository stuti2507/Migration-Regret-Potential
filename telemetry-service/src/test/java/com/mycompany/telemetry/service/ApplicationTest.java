package com.mycompany.telemetry.service;

import com.mycompany.telemetry.service.dto.Telemetry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldCollectTelemetry() {

        Telemetry telemetry = new Telemetry();

        telemetry.setWorkloadId("WL001");
        telemetry.setSourceCloud("AWS");
        telemetry.setTargetCloud("Azure");

        telemetry.setDataVolumeGB(850);
        telemetry.setDataAccessLocality(0.92);

        telemetry.setStatefulSessionRatio(0.65);

        telemetry.setDependencyCount(14);
        telemetry.setDependencyTightness(0.78);

        telemetry.setMigrationHours(42);
        telemetry.setDowntimeHours(4);
        telemetry.setReconfigurationCost(18000);

        telemetry.setPriceVariance(18.5);

        Telemetry response = restTemplate.postForObject(
                "/api/telemetry",
                telemetry,
                Telemetry.class
        );

        assertThat(response).isNotNull();
        assertThat(response.getWorkloadId()).isEqualTo("WL001");
        assertThat(response.getSourceCloud()).isEqualTo("AWS");
        assertThat(response.getTargetCloud()).isEqualTo("Azure");
        assertThat(response.getDataVolumeGB()).isEqualTo(850);
    }
}