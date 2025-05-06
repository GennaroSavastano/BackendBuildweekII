package it.epicode.epic_energy_services.fatture.controller;

import it.epicode.epic_energy_services.fatture.StatoFattura;
import it.epicode.epic_energy_services.fatture.repository.StatoFatturaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stati-fattura")
@RequiredArgsConstructor
public class StatoFatturaController {
    private final StatoFatturaRepository repo;

    @GetMapping
    public ResponseEntity<List<StatoFattura>> getAll() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatoFattura> getOne(@PathVariable Long id) {
        StatoFattura s = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stato non trovato: " + id));
        return ResponseEntity.ok(s);
    }

    @PostMapping
    public ResponseEntity<StatoFattura> create(@Valid @RequestBody StatoFattura s) {
        StatoFattura saved = repo.save(s);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatoFattura> update(@PathVariable Long id, @Valid @RequestBody StatoFattura s) {
        if (!repo.existsById(id)) throw new EntityNotFoundException("Stato non trovato: " + id);
        s.setId(id);
        StatoFattura updated = repo.save(s);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}