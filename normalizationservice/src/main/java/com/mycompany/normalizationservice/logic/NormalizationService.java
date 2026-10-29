package com.mycompany.normalizationservice.logic;

import com.mycompany.normalizationservice.dto.NormalizationRequest;
import com.mycompany.normalizationservice.dto.NormalizationResult;
import org.springframework.stereotype.Service;

@Service
public class NormalizationService {

    public NormalizationResult normalize(NormalizationRequest request) {

        double dgs = normalizeDataGravity(
                request.getDataVolumeGB(),
                request.getDataAccessLocality()
        );

        double ass = clamp(
                request.getStatefulSessionRatio()
        );

        double dci = normalizeDependencyCoupling(
                request.getDependencyCount(),
                request.getDependencyTightness()
        );

        double cvs = normalizeCostVolatility(
                request.getPriceVariance()
        );

        double moc = normalizeMigrationOverhead(
                request.getMigrationHours(),
                request.getDowntimeHours(),
                request.getReconfigurationCost()
        );

        return new NormalizationResult(
                request.getWorkloadId(),
                dgs,
                ass,
                dci,
                cvs,
                moc
        );
    }

    private double normalizeDataGravity(
            double dataVolumeGB,
            double dataAccessLocality) {

        double volumeScore = Math.min(dataVolumeGB / 1000.0, 1.0);

        return clamp(
                0.6 * volumeScore +
                0.4 * dataAccessLocality
        );
    }

    private double normalizeDependencyCoupling(
            int dependencyCount,
            double dependencyTightness) {

        double dependencyScore =
                Math.min(dependencyCount / 20.0, 1.0);

        return clamp(
                0.5 * dependencyScore +
                0.5 * dependencyTightness
        );
    }

    private double normalizeCostVolatility(
            double priceVariance) {

        return clamp(
                priceVariance / 100.0
        );
    }

    private double normalizeMigrationOverhead(
            double migrationHours,
            double downtimeHours,
            double reconfigurationCost) {

        double migrationScore =
                Math.min(migrationHours / 100.0, 1.0);

        double downtimeScore =
                Math.min(downtimeHours / 24.0, 1.0);

        double reconfigurationScore =
                Math.min(reconfigurationCost / 10000.0, 1.0);

        return clamp(
                0.4 * migrationScore +
                0.3 * downtimeScore +
                0.3 * reconfigurationScore
        );
    }

    private double clamp(double value) {

        return Math.max(0.0, Math.min(1.0, value));
    }
}