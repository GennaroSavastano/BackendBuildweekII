package it.epicode.epic_energy_services.fatture.repository;

import it.epicode.epic_energy_services.fatture.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Long>, JpaSpecificationExecutor<Fattura> {
}