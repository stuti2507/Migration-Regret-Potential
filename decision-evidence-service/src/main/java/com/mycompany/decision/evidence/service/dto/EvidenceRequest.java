package com.mycompany.decision.evidence.service.dto;

public class EvidenceRequest {

    private String workloadId;
    private double mrpScore;
    private String decision;
    private double confidence;

    private double dgsContribution;
    private double assContribution;
    private double dciContribution;
    private double cvsContribution;
    private double mocContribution;

    private String outcome;

    public EvidenceRequest() {
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

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public double getDgsContribution() {
        return dgsContribution;
    }

    public void setDgsContribution(double dgsContribution) {
        this.dgsContribution = dgsContribution;
    }

    public double getAssContribution() {
        return assContribution;
    }

    public void setAssContribution(double assContribution) {
        this.assContribution = assContribution;
    }

    public double getDciContribution() {
        return dciContribution;
    }

    public void setDciContribution(double dciContribution) {
        this.dciContribution = dciContribution;
    }

    public double getCvsContribution() {
        return cvsContribution;
    }

    public void setCvsContribution(double cvsContribution) {
        this.cvsContribution = cvsContribution;
    }

    public double getMocContribution() {
        return mocContribution;
    }

    public void setMocContribution(double mocContribution) {
        this.mocContribution = mocContribution;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }
}