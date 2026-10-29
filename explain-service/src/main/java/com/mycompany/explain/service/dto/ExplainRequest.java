package com.mycompany.explain.service.dto;

public class ExplainRequest {

    private String workloadId;

    private double mrpScore;

    private double dgs;
    private double ass;
    private double dci;
    private double cvs;
    private double moc;

    private double alpha = 0.2;
    private double beta = 0.2;
    private double gamma = 0.2;
    private double delta = 0.2;
    private double epsilon = 0.2;

    public ExplainRequest() {
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

    public double getDgs() {
        return dgs;
    }

    public void setDgs(double dgs) {
        this.dgs = dgs;
    }

    public double getAss() {
        return ass;
    }

    public void setAss(double ass) {
        this.ass = ass;
    }

    public double getDci() {
        return dci;
    }

    public void setDci(double dci) {
        this.dci = dci;
    }

    public double getCvs() {
        return cvs;
    }

    public void setCvs(double cvs) {
        this.cvs = cvs;
    }

    public double getMoc() {
        return moc;
    }

    public void setMoc(double moc) {
        this.moc = moc;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }
}