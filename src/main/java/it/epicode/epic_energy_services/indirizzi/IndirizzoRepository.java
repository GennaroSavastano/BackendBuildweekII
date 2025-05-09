package it.epicode.epic_energy_services.indirizzi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndirizzoRepository extends JpaRepository<Indirizzo, Long>{
    @Query("select i from Indirizzo i where i.clienteSedeLegale.id = :id or i.clienteSedeOperativa.id = :id")
    List<Indirizzo> findByClienteId(Long id);
}
