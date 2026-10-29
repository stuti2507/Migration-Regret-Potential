package com.mycompany.normalizationservice.dto;

public class NormalizationResult {

    private String workloadId;

    private double dgs;
    private double ass;
    private double dci;
    private double cvs;
    private double moc;

    public NormalizationResult() {
    }

    public NormalizationResult(String workloadId,
                               double dgs,
                               double ass,
                               double dci,
                               double cvs,
                               double moc) {
        this.workloadId = workloadId;
        this.dgs = dgs;
        this.ass = ass;
        this.dci = dci;
        this.cvs = cvs;
        this.moc = moc;
    }

    public String getWorkloadId() {
        return workloadId;
    }

    public void setWorkloadId(String workloadId) {
        this.workloadId = workloadId;
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
}