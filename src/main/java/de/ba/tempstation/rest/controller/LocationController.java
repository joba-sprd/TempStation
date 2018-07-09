package de.ba.tempstation.rest.controller;

import de.ba.tempstation.db.model.Location;
import de.ba.tempstation.db.repository.EntityRepository;
import de.ba.tempstation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/locations", produces = "application/json")
public class LocationController {

    @Autowired
    EntityRepository<Location> entityRepository;

    @PostMapping(consumes = "application/json")
    public ResponseEntity createLocation(@RequestBody Location location, UriComponentsBuilder builder) {
        Location createdLocation = entityRepository.insertEntity(location);
        URI uri = builder.path("api/locations/{id}").buildAndExpand(createdLocation.getId()).toUri();
        return ResponseEntity.created(uri).body(createdLocation);
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