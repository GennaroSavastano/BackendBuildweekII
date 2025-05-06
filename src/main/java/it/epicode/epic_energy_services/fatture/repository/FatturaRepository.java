package it.epicode.epic_energy_services.fatture.repository;

import it.epicode.epic_energy_services.fatture.Fattura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Long>, JpaSpecificationExecutor<Fattura> {
    Page<Fattura> findByClienteId(Long ClienteId, Pageable pageable);
    Page<Fattura> findByStatoId(Long StatoId , Pageable pageable);
    Page<Fattura> findByDataBetween(LocalDate dataInizio, LocalDate dataFine, Pageable pageable);
    Page<Fattura> findByImportoBetween(BigDecimal min, BigDecimal max, Pageable pageable);
    Page<Fattura> findByNumeroContainingIgnoreCase(String numeroParte, Pageable pageable);
}