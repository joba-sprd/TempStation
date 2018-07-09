package de.ba.tempstation.rest.controller;

import de.ba.tempstation.db.model.MeasuringStation;
import de.ba.tempstation.db.repository.EntityRepository;
import de.ba.tempstation.exception.NotFoundException;
import de.ba.tempstation.rest.dto.CreationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/measuringStations", produces = MediaType.APPLICATION_JSON_VALUE)
public class MeasuringStationController {

    @Autowired
    EntityRepository<MeasuringStation> entityRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createMeasuringStation(@RequestBody MeasuringStation measuringStation, UriComponentsBuilder builder) {
        int id = entityRepository.insertEntity(measuringStation);
        URI uri = builder.path("/api/measuringStations/{id}").buildAndExpand(id).toUri();
        CreationResponseDTO creationResponse = new CreationResponseDTO(id, uri);
        return ResponseEntity.created(uri).body(creationResponse);
    }

    @GetMapping
    public ResponseEntity getMeasuringStations(@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        List<MeasuringStation> measuringStations = entityRepository.getEntities(limit, MeasuringStation.class);
        return ResponseEntity.ok().body(measuringStations);
    }

    @GetMapping("/{measuringStationId}")
    public ResponseEntity getMeasuringStation(@PathVariable("measuringStationId") int id) {
        MeasuringStation measuringStation = entityRepository.getEntityById(id, MeasuringStation.class);
        if (measuringStation == null) {
            throw new NotFoundException();
        }
        return ResponseEntity.ok().body(measuringStation);
    }
}
