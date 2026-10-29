package com.mycompany.mrp.service.dto;

public class MRPResult {

    private String workloadId;
    private double mrpScore;
    private String riskLevel;

    public MRPResult() {
    }

    public MRPResult(String workloadId, double mrpScore, String riskLevel) {
        this.workloadId = workloadId;
        this.mrpScore = mrpScore;
        this.riskLevel = riskLevel;
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

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }
}