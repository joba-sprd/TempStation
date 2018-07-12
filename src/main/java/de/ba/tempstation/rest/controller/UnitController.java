package de.ba.tempstation.rest.controller;

import de.ba.tempstation.db.model.Unit;
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
@RequestMapping(value = "/api/units", produces = MediaType.APPLICATION_JSON_VALUE)
public class UnitController {

    @Autowired
    EntityRepository<Unit> entityRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUnit(@RequestBody Unit unit, UriComponentsBuilder builder) {
        int id = entityRepository.insertEntity(unit);
        URI uri = builder.path("api/units/{id}").buildAndExpand(id).toUri();
        CreationResponseDTO creationResponse = new CreationResponseDTO(id, uri);
        return ResponseEntity.created(uri).body(creationResponse);
    }

    @GetMapping
    public ResponseEntity getCategories(@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        List<Unit> units = entityRepository.getEntities(limit, Unit.class);
        return ResponseEntity.ok().body(units);
    }

    @GetMapping("/{unitId}")
    public ResponseEntity getCategory(@PathVariable("unitId") int id) {
        Unit unit = entityRepository.getEntityById(id, Unit.class);
        if (unit == null) {
            throw new NotFoundException();
        }
        return ResponseEntity.ok().body(unit);
    }
}
