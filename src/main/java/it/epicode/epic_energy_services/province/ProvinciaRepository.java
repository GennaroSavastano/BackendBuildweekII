package it.epicode.epic_energy_services.province;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long>{
    boolean existsBySigla(String sigla);
    Optional<Provincia> findBySigla(String sigla);
}
