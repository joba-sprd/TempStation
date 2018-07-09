package de.ba.tempstation.rest.controller;

import de.ba.tempstation.db.model.Category;
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
@RequestMapping(value = "/api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    @Autowired
    EntityRepository<Category> entityRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCategory(@RequestBody Category category, UriComponentsBuilder builder) {
        int id = entityRepository.insertEntity(category);
        URI uri = builder.path("api/categories/{id}").buildAndExpand(id).toUri();
        CreationResponseDTO creationResponse = new CreationResponseDTO(id, uri);
        return ResponseEntity.created(uri).body(creationResponse);
    }

    @GetMapping
    public ResponseEntity getCategories(@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        List<Category> categories = entityRepository.getEntities(limit, Category.class);
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity getCategory(@PathVariable("categoryId") int id) {
        Category category = entityRepository.getEntityById(id, Category.class);
        if(category == null) {
            throw new NotFoundException();
        }
        return ResponseEntity.ok().body(category);
    }
}
