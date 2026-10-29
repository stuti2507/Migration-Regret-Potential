package com.mycompany.explain.service.logic;

import com.mycompany.explain.service.dto.ExplainRequest;
import com.mycompany.explain.service.dto.ExplainResult;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ExplainService {

    public ExplainResult explain(ExplainRequest request) {

        // Use the configurable weights supplied in the request.
        double alpha = request.getAlpha();
        double beta = request.getBeta();
        double gamma = request.getGamma();
        double delta = request.getDelta();
        double epsilon = request.getEpsilon();

        Map<String, Double> contributions =
                new LinkedHashMap<>();

        double dgsContribution =
                alpha * request.getDgs();

        double assContribution =
                beta * request.getAss();

        double dciContribution =
                gamma * request.getDci();

        double cvsContribution =
                delta * request.getCvs();

        double mocContribution =
                epsilon * request.getMoc();

        double totalContribution =
                dgsContribution
                + assContribution
                + dciContribution
                + cvsContribution
                + mocContribution;

        if (totalContribution == 0) {
            totalContribution = 1;
        }

        double dgsPercentage =
                (dgsContribution / totalContribution) * 100;

        double assPercentage =
                (assContribution / totalContribution) * 100;

        double dciPercentage =
                (dciContribution / totalContribution) * 100;

        double cvsPercentage =
                (cvsContribution / totalContribution) * 100;

        double mocPercentage =
                (mocContribution / totalContribution) * 100;

        contributions.put("DGS", round(dgsPercentage));
        contributions.put("ASS", round(assPercentage));
        contributions.put("DCI", round(dciPercentage));
        contributions.put("CVS", round(cvsPercentage));
        contributions.put("MOC", round(mocPercentage));

        String primaryFactor = "DGS";
        double primaryContribution = dgsPercentage;

        if (assPercentage > primaryContribution) {
            primaryFactor = "ASS";
            primaryContribution = assPercentage;
        }

        if (dciPercentage > primaryContribution) {
            primaryFactor = "DCI";
            primaryContribution = dciPercentage;
        }

        if (cvsPercentage > primaryContribution) {
            primaryFactor = "CVS";
            primaryContribution = cvsPercentage;
        }

        if (mocPercentage > primaryContribution) {
            primaryFactor = "MOC";
            primaryContribution = mocPercentage;
        }

        String factorName = getFactorName(primaryFactor);

        String explanation =
                factorName
                + " contributed "
                + round(primaryContribution)
                + "% of this migration regret score.";

        return new ExplainResult(
                request.getWorkloadId(),
                request.getMrpScore(),
                contributions,
                primaryFactor,
                round(primaryContribution),
                explanation
        );
    }

    private String getFactorName(String factor) {

        switch (factor) {

            case "DGS":
                return "Data Gravity";

            case "ASS":
                return "Application Statefulness";

            case "DCI":
                return "Dependency Coupling";

            case "CVS":
                return "Cost Volatility";

            case "MOC":
                return "Migration Overhead";

            default:
                return "Unknown Factor";
        }
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}