package com.mycompany.explain.service;

import com.mycompany.explain.service.dto.ExplainRequest;
import com.mycompany.explain.service.dto.ExplainResult;
import com.mycompany.explain.service.logic.ExplainService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExplainServiceTest {

    private final ExplainService explainService = new ExplainService();

    @Test
    void shouldCalculateFactorContributions() {

        ExplainRequest request = new ExplainRequest();

        request.setWorkloadId("WL_EXPLAIN_001");
        request.setMrpScore(0.50);

        request.setDgs(0.80);
        request.setAss(0.40);
        request.setDci(0.30);
        request.setCvs(0.20);
        request.setMoc(0.50);

        request.setAlpha(0.40);
        request.setBeta(0.20);
        request.setGamma(0.15);
        request.setDelta(0.10);
        request.setEpsilon(0.15);

        ExplainResult result =
                explainService.explain(request);

        assertEquals(
                "WL_EXPLAIN_001",
                result.getWorkloadId()
        );

        assertEquals(
                0.50,
                result.getMrpScore()
        );

        assertNotNull(result.getContributions());

        /*
         * Weighted contributions:
         *
         * DGS = 0.40 × 0.80 = 0.320
         * ASS = 0.20 × 0.40 = 0.080
         * DCI = 0.15 × 0.30 = 0.045
         * CVS = 0.10 × 0.20 = 0.020
         * MOC = 0.15 × 0.50 = 0.075
         *
         * Total = 0.540
         *
         * Normalized percentages:
         * DGS = 59.26%
         * ASS = 14.81%
         * DCI = 8.33%
         * CVS = 3.70%
         * MOC = 13.89%
         */

        assertEquals(
                59.26,
                result.getContributions().get("DGS")
        );

        assertEquals(
                14.81,
                result.getContributions().get("ASS")
        );

        assertEquals(
                8.33,
                result.getContributions().get("DCI")
        );

        assertEquals(
                3.70,
                result.getContributions().get("CVS")
        );

        assertEquals(
                13.89,
                result.getContributions().get("MOC")
        );
    }

    @Test
    void highestContributionShouldBeDataGravity() {

        ExplainRequest request = new ExplainRequest();

        request.setWorkloadId("WL_EXPLAIN_002");
        request.setMrpScore(0.60);

        request.setDgs(0.90);
        request.setAss(0.20);
        request.setDci(0.20);
        request.setCvs(0.10);
        request.setMoc(0.10);

        request.setAlpha(0.50);
        request.setBeta(0.15);
        request.setGamma(0.15);
        request.setDelta(0.10);
        request.setEpsilon(0.10);

        ExplainResult result =
                explainService.explain(request);

        assertEquals(
                "DGS",
                result.getPrimaryFactor()
        );

        assertEquals(
                "Data Gravity",
                result.getExplanation().substring(
                        0,
                        "Data Gravity".length()
                )
        );
    }

    @Test
    void explanationShouldContainContributionPercentage() {

        ExplainRequest request = new ExplainRequest();

        request.setWorkloadId("WL_EXPLAIN_003");
        request.setMrpScore(0.40);

        request.setDgs(0.50);
        request.setAss(0.30);
        request.setDci(0.20);
        request.setCvs(0.10);
        request.setMoc(0.20);

        request.setAlpha(0.40);
        request.setBeta(0.20);
        request.setGamma(0.15);
        request.setDelta(0.10);
        request.setEpsilon(0.15);

        ExplainResult result =
                explainService.explain(request);

        assertNotNull(result.getExplanation());

        assertTrue(
                result.getExplanation().contains("%")
        );

        assertTrue(
                result.getExplanation()
                        .contains("contributed")
        );
    }
}