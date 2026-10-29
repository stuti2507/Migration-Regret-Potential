package com.mycompany.mrp.service;

import com.mycompany.mrp.service.dto.MRPRequest;
import com.mycompany.mrp.service.dto.MRPResult;
import com.mycompany.mrp.service.logic.MRPService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MRPServiceTest {

    @Test
    void calculateMRP_shouldReturnCorrectScoreAndRisk() {

        MRPRequest request = new MRPRequest();

        request.setWorkloadId("WL001");

        request.setDgs(0.80);
        request.setAss(0.60);
        request.setDci(0.70);
        request.setCvs(0.40);
        request.setMoc(0.90);

        MRPService service = new MRPService();

        MRPResult result = service.calculateMRP(request);

        double expectedScore =
                0.25 * 0.80
                + 0.20 * 0.60
                + 0.20 * 0.70
                + 0.15 * 0.40
                + 0.20 * 0.90;

        assertEquals(expectedScore, result.getMrpScore(), 0.0001);

        assertEquals("WL001", result.getWorkloadId());

        assertEquals("HIGH", result.getRiskLevel());

        assertTrue(result.getMrpScore() >= 0.0);
        assertTrue(result.getMrpScore() <= 1.0);
    }
}