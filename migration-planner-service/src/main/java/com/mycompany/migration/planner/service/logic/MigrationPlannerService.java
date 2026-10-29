package com.mycompany.migration.planner.service.logic;

import com.mycompany.migration.planner.service.dto.MigrationPlan;
import com.mycompany.migration.planner.service.dto.MigrationRequest;
import org.springframework.stereotype.Service;

@Service
public class MigrationPlannerService {

    public MigrationPlan createPlan(MigrationRequest request) {

        String decision = request.getDecision();

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

        } else if ("NO_MIGRATION".equalsIgnoreCase(decision)
                || "DO_NOT_MIGRATE".equalsIgnoreCase(decision)) {

            action = "NO_MIGRATION";
            priority = "LOW";
            executionStatus = "CANCELLED";

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