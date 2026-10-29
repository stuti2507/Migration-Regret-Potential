package com.mycompany.mrp.service.logic;

import com.mycompany.mrp.service.dto.MRPRequest;
import com.mycompany.mrp.service.dto.MRPResult;
import org.springframework.stereotype.Service;

@Service
public class MRPService {

    public MRPResult calculateMRP(MRPRequest request) {

        double mrpScore =
                0.25 * request.getDgs()
                + 0.20 * request.getAss()
                + 0.20 * request.getDci()
                + 0.15 * request.getCvs()
                + 0.20 * request.getMoc();

        mrpScore = clamp(mrpScore);

        String riskLevel = determineRiskLevel(mrpScore);

        return new MRPResult(
                request.getWorkloadId(),
                mrpScore,
                riskLevel
        );
    }

    private String determineRiskLevel(double mrpScore) {

        if (mrpScore <= 0.33) {
            return "LOW";
        } else if (mrpScore <= 0.66) {
            return "MEDIUM";
        } else {
            return "HIGH";
        }
    }

    private double clamp(double value) {

        return Math.max(0.0, Math.min(1.0, value));
    }
}