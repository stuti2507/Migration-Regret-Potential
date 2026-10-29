package com.mycompany.migration.planner.service.dto;

public class MigrationPlan {

    private String workloadId;
    private String action;
    private String sourceCloud;
    private String targetCloud;
    private String priority;
    private String executionStatus;

    public MigrationPlan() {
    }

    public MigrationPlan(
            String workloadId,
            String action,
            String sourceCloud,
            String targetCloud,
            String priority,
            String executionStatus) {

        this.workloadId = workloadId;
        this.action = action;
        this.sourceCloud = sourceCloud;
        this.targetCloud = targetCloud;
        this.priority = priority;
        this.executionStatus = executionStatus;
    }

    public String getWorkloadId() {
        return workloadId;
    }

    public void setWorkloadId(String workloadId) {
        this.workloadId = workloadId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(String executionStatus) {
        this.executionStatus = executionStatus;
    }
}