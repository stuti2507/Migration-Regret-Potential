package com.mycompany.telemetry.service.dto;

public class Telemetry {

    private String workloadId;
    private String sourceCloud;
    private String targetCloud;

    // Data Gravity inputs
    private double dataVolumeGB;
    private double dataAccessLocality;

    // Application Statefulness
    private double statefulSessionRatio;

    // Dependency Coupling
    private int dependencyCount;
    private double dependencyTightness;

    // Migration Overhead
    private double migrationHours;
    private double downtimeHours;
    private double reconfigurationCost;

    // Cost Volatility
    private double priceVariance;

    public Telemetry() {
    }

    public String getWorkloadId() {
        return workloadId;
    }

    public void setWorkloadId(String workloadId) {
        this.workloadId = workloadId;
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

    public double getDataVolumeGB() {
        return dataVolumeGB;
    }

    public void setDataVolumeGB(double dataVolumeGB) {
        this.dataVolumeGB = dataVolumeGB;
    }

    public double getDataAccessLocality() {
        return dataAccessLocality;
    }

    public void setDataAccessLocality(double dataAccessLocality) {
        this.dataAccessLocality = dataAccessLocality;
    }

    public double getStatefulSessionRatio() {
        return statefulSessionRatio;
    }

    public void setStatefulSessionRatio(double statefulSessionRatio) {
        this.statefulSessionRatio = statefulSessionRatio;
    }

    public int getDependencyCount() {
        return dependencyCount;
    }

    public void setDependencyCount(int dependencyCount) {
        this.dependencyCount = dependencyCount;
    }

    public double getDependencyTightness() {
        return dependencyTightness;
    }

    public void setDependencyTightness(double dependencyTightness) {
        this.dependencyTightness = dependencyTightness;
    }

    public double getMigrationHours() {
        return migrationHours;
    }

    public void setMigrationHours(double migrationHours) {
        this.migrationHours = migrationHours;
    }

    public double getDowntimeHours() {
        return downtimeHours;
    }

    public void setDowntimeHours(double downtimeHours) {
        this.downtimeHours = downtimeHours;
    }

    public double getReconfigurationCost() {
        return reconfigurationCost;
    }

    public void setReconfigurationCost(double reconfigurationCost) {
        this.reconfigurationCost = reconfigurationCost;
    }

    public double getPriceVariance() {
        return priceVariance;
    }

    public void setPriceVariance(double priceVariance) {
        this.priceVariance = priceVariance;
    }
}