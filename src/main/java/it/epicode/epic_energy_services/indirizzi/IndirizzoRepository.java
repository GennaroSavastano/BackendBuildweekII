package it.epicode.epic_energy_services.indirizzi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndirizzoRepository extends JpaRepository<Indirizzo, Long>{
}
