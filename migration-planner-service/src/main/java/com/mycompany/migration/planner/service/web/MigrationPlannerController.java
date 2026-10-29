package com.mycompany.migration.planner.service.web;

import com.mycompany.migration.planner.service.dto.FullMigrationRequest;
import com.mycompany.migration.planner.service.dto.MigrationPlan;
import com.mycompany.migration.planner.service.dto.MigrationRequest;
import com.mycompany.migration.planner.service.logic.MigrationPlannerService;
import com.mycompany.migration.planner.service.logic.OrchestrationService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/migration")
public class MigrationPlannerController {

    private final MigrationPlannerService migrationPlannerService;
    private final OrchestrationService orchestrationService;

    public MigrationPlannerController(
            MigrationPlannerService migrationPlannerService,
            OrchestrationService orchestrationService) {

        this.migrationPlannerService = migrationPlannerService;
        this.orchestrationService = orchestrationService;
    }

    @PostMapping
    public MigrationPlan createPlan(
            @RequestBody MigrationRequest request) {

        return migrationPlannerService.createPlan(request);
    }

    @PostMapping("/orchestrate")
    public Map<String, Object> orchestrate(
            @RequestBody FullMigrationRequest request) {

        return orchestrationService.orchestrate(request);
    }

    @GetMapping("/health")
    public String health() {
        return "Migration Planner Service is running";
    }
}