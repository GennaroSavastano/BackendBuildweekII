package it.epicode.epic_energy_services.fatture.service;

import it.epicode.epic_energy_services.fatture.Fattura;
import it.epicode.epic_energy_services.fatture.repository.FatturaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FatturaService {

    private final FatturaRepository repository;

    public Page<Fattura> listAll(Specification<Fattura> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    public Fattura getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fattura non trovata: " + id));
    }

    public Fattura create(Fattura f) {
        return repository.save(f);
    }

    public Fattura update(Long id, Fattura f) {
        getById(id); // lancia se non esiste
        f.setId(id);
        return repository.save(f);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}