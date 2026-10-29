package com.mycompany.decision.service;

import com.mycompany.decision.service.dto.DecisionRequest;
import com.mycompany.decision.service.dto.DecisionResult;
import com.mycompany.decision.service.logic.DecisionService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DecisionServiceTest {

    private final DecisionService decisionService = new DecisionService();

    @Test
    void lowRiskShouldRecommendMigration() {

        DecisionRequest request = new DecisionRequest();

        request.setWorkloadId("WL_LOW");
        request.setMrpScore(0.20);

        DecisionResult result =
                decisionService.makeDecision(request);

        assertEquals("MIGRATE", result.getDecision());
        assertEquals(
                "Low migration regret potential",
                result.getReason()
        );
    }

    @Test
    void mediumRiskShouldRecommendReview() {

        DecisionRequest request = new DecisionRequest();

        request.setWorkloadId("WL_MEDIUM");
        request.setMrpScore(0.50);

        DecisionResult result =
                decisionService.makeDecision(request);

        assertEquals("REVIEW", result.getDecision());
        assertEquals(
                "Moderate migration regret potential",
                result.getReason()
        );
    }

    @Test
    void highRiskShouldRecommendNoMigration() {

        DecisionRequest request = new DecisionRequest();

        request.setWorkloadId("WL_HIGH");
        request.setMrpScore(0.80);

        DecisionResult result =
                decisionService.makeDecision(request);

        assertEquals("NO_MIGRATION", result.getDecision());
        assertEquals(
                "High migration regret potential",
                result.getReason()
        );
    }
}