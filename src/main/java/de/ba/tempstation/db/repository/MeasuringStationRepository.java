package de.ba.tempstation.db.repository;

import de.ba.tempstation.db.model.MeasuringStation;
import org.springframework.stereotype.Repository;

@Repository
public class MeasuringStationRepository extends EntityRepository<MeasuringStation>{

    public MeasuringStation getMeasuringStationByHardwareId(String hardwareId) {
        return null;
    }
}
