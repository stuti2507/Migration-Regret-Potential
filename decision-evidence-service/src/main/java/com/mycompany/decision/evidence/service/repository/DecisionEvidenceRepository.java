package com.mycompany.decision.evidence.service.repository;

import com.mycompany.decision.evidence.service.entity.DecisionEvidence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DecisionEvidenceRepository
        extends JpaRepository<DecisionEvidence, Long> {

    Optional<DecisionEvidence> findByWorkloadId(String workloadId);
}