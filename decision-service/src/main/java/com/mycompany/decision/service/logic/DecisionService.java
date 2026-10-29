package com.mycompany.decision.service.logic;

import com.mycompany.decision.service.dto.DecisionRequest;
import com.mycompany.decision.service.dto.DecisionResult;
import org.springframework.stereotype.Service;

@Service
public class DecisionService {

    public DecisionResult makeDecision(DecisionRequest request) {

        double mrpScore = request.getMrpScore();

        String decision;
        String reason;

        if (mrpScore <= 0.33) {
            decision = "MIGRATE";
            reason = "Low migration regret potential";

        } else if (mrpScore <= 0.66) {
            decision = "REVIEW";
            reason = "Moderate migration regret potential";

        } else {
            decision = "NO_MIGRATION";
            reason = "High migration regret potential";
        }

        return new DecisionResult(
                request.getWorkloadId(),
                mrpScore,
                decision,
                reason
        );
    }
}