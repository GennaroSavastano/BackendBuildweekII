package it.epicode.epic_energy_services.fatture.controller;

import it.epicode.epic_energy_services.fatture.Fattura;
import it.epicode.epic_energy_services.fatture.service.FatturaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fatture")
@RequiredArgsConstructor
public class FatturaController {

    private final FatturaService service;

    @GetMapping
    public ResponseEntity<Page<Fattura>> getAll(
            Specification<Fattura> spec,
            @PageableDefault(size = 20, sort = "data", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(service.listAll(spec, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fattura> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Fattura> create(@Valid @RequestBody Fattura f) {
        Fattura created = service.create(f);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fattura> update(@PathVariable Long id, @Valid @RequestBody Fattura f) {
        return ResponseEntity.ok(service.update(id, f));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}