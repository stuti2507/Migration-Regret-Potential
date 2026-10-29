package com.mycompany.migration.planner.service.dto;

public class MigrationRequest {

    private String workloadId;
    private String decision;
    private String sourceCloud;
    private String targetCloud;
    private double mrpScore;

    public MigrationRequest() {
    }

    public String getWorkloadId() {
        return workloadId;
    }

    public void setWorkloadId(String workloadId) {
        this.workloadId = workloadId;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getSourceCloud() {
        return sourceCloud;
    }

    public void setSourceCloud(String sourceCloud) {
        this.sourceCloud = sourceCloud;
    }

    public String getTargetCloud() {
        return targetCloud;
    }

    public void setTargetCloud(String targetCloud) {
        this.targetCloud = targetCloud;
    }

    public double getMrpScore() {
        return mrpScore;
    }

    public void setMrpScore(double mrpScore) {
        this.mrpScore = mrpScore;
    }
}