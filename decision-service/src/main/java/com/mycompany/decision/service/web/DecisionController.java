package com.mycompany.decision.service.web;

import com.mycompany.decision.service.dto.DecisionRequest;
import com.mycompany.decision.service.dto.DecisionResult;
import com.mycompany.decision.service.logic.DecisionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/decision")
public class DecisionController {

    private final DecisionService decisionService;

    public DecisionController(DecisionService decisionService) {
        this.decisionService = decisionService;
    }

    @PostMapping
    public DecisionResult makeDecision(
            @RequestBody DecisionRequest request) {

        return decisionService.makeDecision(request);
    }

    @GetMapping("/health")
    public String health() {
        return "Decision Service is running";
    }
}