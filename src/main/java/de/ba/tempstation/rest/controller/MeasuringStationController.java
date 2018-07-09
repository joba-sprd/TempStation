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
@RequestMapping(value = "/measuringStations", produces = "application/json")
public class MeasuringStationController {

    @Autowired
    MeasuringStationRepository measuringStationRepository;

    @PostMapping(consumes = "application/json")
    public ResponseEntity createMeasuringStation(@RequestBody MeasuringStation station) {
        MeasuringStation measuringStation = measuringStationRepository.insertMeasuringStation(station);
        return ResponseEntity.status(HttpStatus.CREATED).body(measuringStation);
    }

    @GetMapping
    public ResponseEntity getMeasuringStations(@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        List<MeasuringStation> measuringStations = measuringStationRepository.getMeasuringStations(limit);
        return ResponseEntity.ok().body(measuringStations);
    }

    @GetMapping("/{measuringStationId}")
    public ResponseEntity getMeasuringStationById(@PathVariable("measuringStationId") int id) {
        MeasuringStation measuringStation = measuringStationRepository.getMeasuringStationById(id);
        if (measuringStation == null) {
            throw new NotFoundException();
        }
        return ResponseEntity.ok().body(measuringStation);
    }
}
