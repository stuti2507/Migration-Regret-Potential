package com.mycompany.explain.service.web;

import com.mycompany.explain.service.dto.ExplainRequest;
import com.mycompany.explain.service.dto.ExplainResult;
import com.mycompany.explain.service.logic.ExplainService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/explain")
public class ExplainController {

    private final ExplainService explainService;

    public ExplainController(ExplainService explainService) {
        this.explainService = explainService;
    }

    @PostMapping
    public ExplainResult explain(
            @RequestBody ExplainRequest request) {

        return explainService.explain(request);
    }

    @GetMapping("/health")
    public String health() {
        return "Explain Service is running";
    }
}