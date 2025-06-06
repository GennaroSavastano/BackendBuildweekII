package it.epicode.epic_energy_services.clienti.repository;

import it.epicode.epic_energy_services.clienti.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {
    Page<Cliente> findAllByOrderByNomeContattoAsc(Pageable pageable);
    Page<Cliente> findAllByOrderByFatturatoAnnualeAsc(Pageable pageable);
    Page<Cliente> findAllByOrderByDataInserimentoAsc(Pageable pageable);
    Page<Cliente> findAllByOrderByDataUltimoContattoAsc(Pageable pageable);

    Page<Cliente> findAllByOrderByIndirizzoSedeLegale_Comune_ProvinciaDaCsvAsc(Pageable pageable);

    Page<Cliente> findByFatturatoAnnualeBetween(int min, int max, Pageable pageable);
    Page<Cliente> findByDataInserimentoGreaterThanEqual(LocalDate dataInserimento, Pageable pageable);
    Page<Cliente> findByDataUltimoContattoGreaterThanEqual(LocalDate dataUltimoContatto, Pageable pageable);
    Page<Cliente> findByNomeContattoContainingIgnoreCase(String nomeContatto, Pageable pageable);
    Page<Cliente> findByCognomeContattoContainingIgnoreCase(String cognomeContatto, Pageable pageable);
}
