package com.mycompany.decision.evidence.service.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "decision_evidence")
public class DecisionEvidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String workloadId;

    @Column(nullable = false)
    private double mrpScore;

    @Column(nullable = false)
    private String decision;

    @Column(nullable = false)
    private double confidence;

    @Column(nullable = false)
    private double dgsContribution;

    @Column(nullable = false)
    private double assContribution;

    @Column(nullable = false)
    private double dciContribution;

    @Column(nullable = false)
    private double cvsContribution;

    @Column(nullable = false)
    private double mocContribution;

    @Column(nullable = false)
    private String outcome;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public DecisionEvidence() {
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}