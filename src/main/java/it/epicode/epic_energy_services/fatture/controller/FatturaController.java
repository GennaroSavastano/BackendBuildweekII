package it.epicode.epic_energy_services.fatture.controller;

import it.epicode.epic_energy_services.fatture.Fattura;
import it.epicode.epic_energy_services.fatture.service.FatturaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequestMapping("/api/fatture")
@RequiredArgsConstructor
public class FatturaController {
    private final FatturaService service;

    @GetMapping
    public ResponseEntity<Page<Fattura>> getAll(
            @RequestParam(required = false) Long clienteId,
            @RequestParam(required = false) Long statoId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) BigDecimal minImporto,
            @RequestParam(required = false) BigDecimal maxImporto,
            @RequestParam(required = false) String numeroParte,
            @PageableDefault(size = 20, sort = "data", direction = DESC) Pageable pageable) {
        if (clienteId != null) return ResponseEntity.ok(service.byCliente(clienteId, pageable));
        if (statoId != null)   return ResponseEntity.ok(service.byStato(statoId, pageable));
        if (startDate != null || endDate != null) return ResponseEntity.ok(service.byDataRange(startDate, endDate, pageable));
        if (minImporto != null || maxImporto != null) return ResponseEntity.ok(service.byImportoRange(minImporto, maxImporto, pageable));
        if (numeroParte != null) return ResponseEntity.ok(service.byNumero(numeroParte, pageable));
        return ResponseEntity.ok(service.getAll(pageable));
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