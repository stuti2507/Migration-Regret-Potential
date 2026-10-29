package com.mycompany.explain.service.dto;

import java.util.Map;

public class ExplainResult {

    private String workloadId;
    private double mrpScore;

    private Map<String, Double> contributions;

    private String primaryFactor;
    private double primaryContribution;

    private String explanation;

    public ExplainResult(
            String workloadId,
            double mrpScore,
            Map<String, Double> contributions,
            String primaryFactor,
            double primaryContribution,
            String explanation) {

        this.workloadId = workloadId;
        this.mrpScore = mrpScore;
        this.contributions = contributions;
        this.primaryFactor = primaryFactor;
        this.primaryContribution = primaryContribution;
        this.explanation = explanation;
    }

    public String getWorkloadId() {
        return workloadId;
    }

    public double getMrpScore() {
        return mrpScore;
    }

    public Map<String, Double> getContributions() {
        return contributions;
    }

    public String getPrimaryFactor() {
        return primaryFactor;
    }

    public double getPrimaryContribution() {
        return primaryContribution;
    }

    public String getExplanation() {
        return explanation;
    }
}