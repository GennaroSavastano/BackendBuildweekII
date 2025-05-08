package it.epicode.epic_energy_services.utenti.repository;

import it.epicode.epic_energy_services.utenti.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RuoloRepository extends JpaRepository<Ruolo, Long> {
    Optional<Ruolo> findByNome(String nome);
}