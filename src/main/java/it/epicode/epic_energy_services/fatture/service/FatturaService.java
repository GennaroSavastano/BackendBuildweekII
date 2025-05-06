package it.epicode.epic_energy_services.fatture.service;

import it.epicode.epic_energy_services.fatture.Fattura;
import it.epicode.epic_energy_services.fatture.repository.FatturaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class FatturaService {
    private final FatturaRepository repository;

    public Page<Fattura> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    public Page<Fattura> byCliente(Long clienteId, Pageable pageable) {
        return repository.findByClienteId(clienteId, pageable);
    }
    public Page<Fattura> byStato(Long statoId, Pageable pageable) {
        return repository.findByStatoId(statoId, pageable);
    }
    public Page<Fattura> byDataRange(LocalDate start, LocalDate end, Pageable pageable) {
        return repository.findByDataBetween(start, end, pageable);
    }
    public Page<Fattura> byImportoRange(BigDecimal min, BigDecimal max, Pageable pageable) {
        return repository.findByImportoBetween(min, max, pageable);
    }
    public Page<Fattura> byNumero(String numeroParte, Pageable pageable) {
        return repository.findByNumeroContainingIgnoreCase(numeroParte, pageable);
    }
    public Fattura getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fattura non trovata: " + id));
    }
    public Fattura create(Fattura f) {
        return repository.save(f);
    }
    public Fattura update(Long id, Fattura f) {
        getById(id);
        f.setId(id);
        return repository.save(f);
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
}