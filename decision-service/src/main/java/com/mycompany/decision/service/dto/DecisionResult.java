package com.mycompany.decision.service.dto;

public class DecisionResult {

    private String workloadId;
    private double mrpScore;
    private String decision;
    private String reason;

    public DecisionResult() {
    }

    public DecisionResult(
            String workloadId,
            double mrpScore,
            String decision,
            String reason) {

        this.workloadId = workloadId;
        this.mrpScore = mrpScore;
        this.decision = decision;
        this.reason = reason;
    }

    public String getWorkloadId() {
        return workloadId;
    }

    public void setWorkloadId(String workloadId) {
        this.workloadId = workloadId;
    }

    public double getMrpScore() {
        return mrpScore;
    }

    public void setMrpScore(double mrpScore) {
        this.mrpScore = mrpScore;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}