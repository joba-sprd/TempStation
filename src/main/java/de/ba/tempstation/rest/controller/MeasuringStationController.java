package de.ba.tempstation.rest.controller;

import de.ba.tempstation.db.model.MeasuringStation;
import de.ba.tempstation.db.repository.MeasuringStationRepository;
import de.ba.tempstation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/measuringStations", produces = "application/json")
public class MeasuringStationController {

    @Autowired
    MeasuringStationRepository measuringStationRepository;

    @PostMapping(consumes = "application/json")
    public ResponseEntity createMeasuringStation(@RequestBody MeasuringStation station) {
        MeasuringStation measuringStation = measuringStationRepository.insertEntity(station);
        return ResponseEntity.status(HttpStatus.CREATED).body(measuringStation);
    }

    @GetMapping
    public ResponseEntity getMeasuringStations(@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        List<MeasuringStation> measuringStations = measuringStationRepository.getEntities(limit, MeasuringStation.class);
        return ResponseEntity.ok().body(measuringStations);
    }

    @GetMapping("/{measuringStationId}")
    public ResponseEntity getMeasuringStationById(@PathVariable("measuringStationId") int id) {
        MeasuringStation measuringStation = measuringStationRepository.getEntityById(id, MeasuringStation.class);
        if (measuringStation == null) {
            throw new NotFoundException();
        }
        return ResponseEntity.ok().body(measuringStation);
    }
}
