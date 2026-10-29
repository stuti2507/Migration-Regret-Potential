package com.mycompany.decision.evidence.service.logic;

import com.mycompany.decision.evidence.service.client.ExplainServiceClient;
import com.mycompany.decision.evidence.service.dto.EvidenceRequest;
import com.mycompany.decision.evidence.service.entity.DecisionEvidence;
import com.mycompany.decision.evidence.service.repository.DecisionEvidenceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class EvidenceService {

    private final DecisionEvidenceRepository repository;
    private final ExplainServiceClient explainServiceClient;

    public EvidenceService(
            DecisionEvidenceRepository repository,
            ExplainServiceClient explainServiceClient) {

        this.repository = repository;
        this.explainServiceClient = explainServiceClient;
    }

    public DecisionEvidence saveEvidence(
            EvidenceRequest request) {

        Map<String, Object> explainRequest =
                new HashMap<>();

        explainRequest.put(
                "workloadId",
                request.getWorkloadId());

        explainRequest.put(
                "mrpScore",
                request.getMrpScore());

        explainRequest.put(
                "dgs",
                request.getDgsContribution());

        explainRequest.put(
                "ass",
                request.getAssContribution());

        explainRequest.put(
                "dci",
                request.getDciContribution());

        explainRequest.put(
                "cvs",
                request.getCvsContribution());

        explainRequest.put(
                "moc",
                request.getMocContribution());

        // MRP weights
        // DGS = 0.25
        // ASS = 0.20
        // DCI = 0.20
        // CVS = 0.15
        // MOC = 0.20

        explainRequest.put(
                "alpha",
                0.25);

        explainRequest.put(
                "beta",
                0.20);

        explainRequest.put(
                "gamma",
                0.20);

        explainRequest.put(
                "delta",
                0.15);

        explainRequest.put(
                "epsilon",
                0.20);

        Map<String, Object> explanation =
                explainServiceClient.getExplanation(
                        explainRequest);

        Map<String, Object> contributions =
                (Map<String, Object>)
                        explanation.get("contributions");

        DecisionEvidence evidence =
                new DecisionEvidence();

        evidence.setWorkloadId(
                request.getWorkloadId());

        evidence.setMrpScore(
                request.getMrpScore());

        evidence.setDecision(
                request.getDecision());

        evidence.setConfidence(
                request.getConfidence());

        evidence.setDgsContribution(
                ((Number) contributions.get("DGS"))
                        .doubleValue());

        evidence.setAssContribution(
                ((Number) contributions.get("ASS"))
                        .doubleValue());

        evidence.setDciContribution(
                ((Number) contributions.get("DCI"))
                        .doubleValue());

        evidence.setCvsContribution(
                ((Number) contributions.get("CVS"))
                        .doubleValue());

        evidence.setMocContribution(
                ((Number) contributions.get("MOC"))
                        .doubleValue());

        evidence.setOutcome(
                request.getOutcome());

        evidence.setTimestamp(
                LocalDateTime.now());

        return repository.save(evidence);
    }

    public Optional<DecisionEvidence> getEvidenceByWorkloadId(
            String workloadId) {

        return repository.findByWorkloadId(workloadId);
    }
}