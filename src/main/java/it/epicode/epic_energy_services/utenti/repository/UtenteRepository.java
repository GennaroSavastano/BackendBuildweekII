package it.epicode.epic_energy_services.utenti.repository;

import it.epicode.epic_energy_services.utenti.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Optional<Utente> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}