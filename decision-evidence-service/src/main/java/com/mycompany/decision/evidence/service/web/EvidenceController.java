package com.mycompany.decision.evidence.service.web;

import com.mycompany.decision.evidence.service.dto.EvidenceRequest;
import com.mycompany.decision.evidence.service.entity.DecisionEvidence;
import com.mycompany.decision.evidence.service.logic.EvidenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evidence")
public class EvidenceController {

    private final EvidenceService evidenceService;

    public EvidenceController(
            EvidenceService evidenceService) {

        this.evidenceService = evidenceService;
    }

    @PostMapping
    public ResponseEntity<DecisionEvidence> saveEvidence(
            @RequestBody EvidenceRequest request) {

        DecisionEvidence evidence =
                evidenceService.saveEvidence(request);

        return ResponseEntity.ok(evidence);
    }

    @GetMapping("/{workloadId}")
    public ResponseEntity<DecisionEvidence> getEvidence(
            @PathVariable String workloadId) {

        return evidenceService
                .getEvidenceByWorkloadId(workloadId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("decision-evidence-service is healthy");
    }
}