package it.epicode.epic_energy_services.fatture.repository;

import it.epicode.epic_energy_services.fatture.StatoFattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatoFatturaRepository extends JpaRepository<StatoFattura, Long> {
}