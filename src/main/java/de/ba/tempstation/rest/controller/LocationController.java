package de.ba.tempstation.rest.controller;

import de.ba.tempstation.db.model.Location;
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
@RequestMapping(value = "/api/locations", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocationController {

    @Autowired
    EntityRepository<Location> entityRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createLocation(@RequestBody Location location, UriComponentsBuilder builder) {
        int id = entityRepository.insertEntity(location);
        URI uri = builder.path("api/locations/{id}").buildAndExpand(id).toUri();
        CreationResponseDTO creationResponse = new CreationResponseDTO(id, uri);
        return ResponseEntity.created(uri).body(creationResponse);
    }

    @GetMapping
    public ResponseEntity getLocations(@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        List<Location> locations = entityRepository.getEntities(limit, Location.class);
        return ResponseEntity.ok().body(locations);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity getLocation(@PathVariable("locationId") int id) {
        Location location = entityRepository.getEntityById(id, Location.class);
        if (location == null) {
            throw new NotFoundException();
        }
        return ResponseEntity.ok().body(location);
    }
}