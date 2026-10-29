package com.mycompany.migration.planner.service.logic;

import com.mycompany.migration.planner.service.dto.FullMigrationRequest;
import com.mycompany.migration.planner.service.dto.MigrationPlan;
import com.mycompany.migration.planner.service.dto.MigrationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class OrchestrationService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> orchestrate(FullMigrationRequest request) {

        Map<String, Object> result = new LinkedHashMap<>();

        // 1. Telemetry Service
        Map<String, Object> telemetry =
                restTemplate.postForObject(
                        "http://localhost:8081/api/telemetry",
                        request,
                        Map.class
                );

        result.put("telemetry", telemetry);

        // 2. Normalization Service
        Map<String, Object> normalized =
                restTemplate.postForObject(
                        "http://localhost:8082/api/normalize",
                        request,
                        Map.class
                );

        result.put("normalization", normalized);

        // 3. MRP Service
        Map<String, Object> mrpRequest = new LinkedHashMap<>();

        mrpRequest.put("workloadId", request.getWorkloadId());
        mrpRequest.put("dgs", normalized.get("dgs"));
        mrpRequest.put("ass", normalized.get("ass"));
        mrpRequest.put("dci", normalized.get("dci"));
        mrpRequest.put("cvs", normalized.get("cvs"));
        mrpRequest.put("moc", normalized.get("moc"));

        Map<String, Object> mrp =
                restTemplate.postForObject(
                        "http://localhost:8083/api/mrp",
                        mrpRequest,
                        Map.class
                );

        result.put("mrp", mrp);

        // 4. Decision Service
        Map<String, Object> decisionRequest = new LinkedHashMap<>();

        decisionRequest.put("workloadId", request.getWorkloadId());
        decisionRequest.put("mrpScore", mrp.get("mrpScore"));

        Map<String, Object> decision =
                restTemplate.postForObject(
                        "http://localhost:8084/api/decision",
                        decisionRequest,
                        Map.class
                );

        result.put("decision", decision);

        // 5. Explain Service
        Map<String, Object> explainRequest = new LinkedHashMap<>();

        explainRequest.put("workloadId", request.getWorkloadId());
        explainRequest.put("mrpScore", mrp.get("mrpScore"));

        explainRequest.put("dgs", normalized.get("dgs"));
        explainRequest.put("ass", normalized.get("ass"));
        explainRequest.put("dci", normalized.get("dci"));
        explainRequest.put("cvs", normalized.get("cvs"));
        explainRequest.put("moc", normalized.get("moc"));

        // Same weights used by the MRP and Evidence layers
        explainRequest.put("alpha", 0.25);
        explainRequest.put("beta", 0.20);
        explainRequest.put("gamma", 0.20);
        explainRequest.put("delta", 0.15);
        explainRequest.put("epsilon", 0.20);

        Map<String, Object> explanation =
                restTemplate.postForObject(
                        "http://localhost:8086/api/explain",
                        explainRequest,
                        Map.class
                );

        result.put("explanation", explanation);

        // 6. Decision Evidence Service
        Map<String, Object> evidenceRequest = new LinkedHashMap<>();

        evidenceRequest.put(
                "workloadId",
                request.getWorkloadId()
        );

        evidenceRequest.put(
                "mrpScore",
                mrp.get("mrpScore")
        );

        evidenceRequest.put(
                "decision",
                decision.get("decision")
        );

        evidenceRequest.put(
                "confidence",
                request.getConfidence()
        );

        /*
         * IMPORTANT:
         * EvidenceService expects these fields to contain
         * the normalized input factors, not the already-calculated
         * explanation percentages.
         *
         * EvidenceService then applies the same MRP weights and
         * independently reconstructs the contribution percentages.
         */
        evidenceRequest.put(
                "dgsContribution",
                normalized.get("dgs")
        );

        evidenceRequest.put(
                "assContribution",
                normalized.get("ass")
        );

        evidenceRequest.put(
                "dciContribution",
                normalized.get("dci")
        );

        evidenceRequest.put(
                "cvsContribution",
                normalized.get("cvs")
        );

        evidenceRequest.put(
                "mocContribution",
                normalized.get("moc")
        );

        evidenceRequest.put(
                "outcome",
                request.getOutcome()
        );

        Map<String, Object> evidence =
                restTemplate.postForObject(
                        "http://localhost:8087/api/evidence",
                        evidenceRequest,
                        Map.class
                );

        result.put("evidence", evidence);

        // 7. Migration Planner
        MigrationRequest migrationRequest =
                new MigrationRequest();

        migrationRequest.setWorkloadId(
                request.getWorkloadId()
        );

        migrationRequest.setSourceCloud(
                request.getSourceCloud()
        );

        migrationRequest.setTargetCloud(
                request.getTargetCloud()
        );

        migrationRequest.setMrpScore(
                ((Number) mrp.get("mrpScore")).doubleValue()
        );

        migrationRequest.setDecision(
                String.valueOf(
                        decision.get("decision")
                )
        );

        MigrationPlan migrationPlan =
                createPlan(migrationRequest);

        result.put(
                "migrationPlan",
                migrationPlan
        );

        return result;
    }

    private MigrationPlan createPlan(
            MigrationRequest request) {

        String decision =
                request.getDecision();

        String action;
        String priority;
        String executionStatus;

        if ("MIGRATE".equalsIgnoreCase(decision)) {

            action = "MIGRATE";

            if (request.getMrpScore() <= 0.20) {
                priority = "HIGH";
            } else {
                priority = "NORMAL";
            }

            executionStatus = "PLANNED";

        } else if ("REVIEW".equalsIgnoreCase(decision)) {

            action = "MANUAL_REVIEW";
            priority = "MEDIUM";
            executionStatus = "PENDING_REVIEW";

        } else {

            action = "NO_MIGRATION";
            priority = "LOW";
            executionStatus = "CANCELLED";
        }

        return new MigrationPlan(
                request.getWorkloadId(),
                action,
                request.getSourceCloud(),
                request.getTargetCloud(),
                priority,
                executionStatus
        );
    }
}