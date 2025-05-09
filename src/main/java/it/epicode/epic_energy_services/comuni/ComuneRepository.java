package it.epicode.epic_energy_services.comuni;

import it.epicode.epic_energy_services.province.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, Long>{
   Optional <Comune> findByNome(String nome);
    boolean existsByNome(String nome);
    boolean existsByNomeAndProvincia(String nome, Provincia provincia);
    Optional<Comune> findByNomeAndProvincia(String nome, Provincia provincia);
}
