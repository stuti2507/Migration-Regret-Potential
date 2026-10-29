package com.mycompany.normalizationservice.web;

import com.mycompany.normalizationservice.dto.NormalizationRequest;
import com.mycompany.normalizationservice.dto.NormalizationResult;
import com.mycompany.normalizationservice.logic.NormalizationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/normalize")
public class NormalizationController {

    private final NormalizationService normalizationService;

    public NormalizationController(NormalizationService normalizationService) {
        this.normalizationService = normalizationService;
    }

    @PostMapping
    public NormalizationResult normalize(
            @RequestBody NormalizationRequest request) {

        return normalizationService.normalize(request);
    }

    @GetMapping("/health")
    public String health() {
        return "Normalization Service is running";
    }
}