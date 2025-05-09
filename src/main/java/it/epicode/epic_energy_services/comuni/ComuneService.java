package it.epicode.epic_energy_services.comuni;

import it.epicode.epic_energy_services.province.Provincia;
import it.epicode.epic_energy_services.province.ProvinciaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ComuneService {

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private ProvinciaService provinciaService;

    // tutti i comuni
    public Page<Comune> findAllComuni(Pageable pageable) {
        return comuneRepository.findAll(pageable);
    }

    // comune da id
    public Comune findComuneById(Long id) {
        return comuneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comune non trovato"));
    }

    // comune da nome
    public Comune findComuneByNome(String nome) {
        return comuneRepository.findByNome(nome)
                .orElseThrow(() -> new EntityNotFoundException("Comune non trovato"));
    }

    // salva comune con riferimento a provincia da id
    public Comune savaComune(Comune comune) {
        if (comuneRepository.existsByNomeAndProvincia(comune.getNome(), comune.getProvincia())) {
            throw new IllegalArgumentException("Comune già esistente");
        }
        if (provinciaService.findProvinciaById(comune.getProvincia().getId()) == null) {
            throw new EntityNotFoundException("Provincia non trovata");
        }
        return comuneRepository.save(comune);
    }

    // Aggiorna comune esistente
    public Comune updateComune(Long id, Comune comuneModificato) {
        Comune comune = findComuneById(id);
        comune.setNome(comuneModificato.getNome());
        // aggiorna la provincia se fornita e valida
        if (comuneModificato.getProvincia() != null && comuneModificato.getProvincia().getId() != null) {
            Provincia nuovaProvincia = provinciaService.findProvinciaById(comuneModificato.getProvincia().getId());
            comune.setProvincia(nuovaProvincia);
        }
        return comuneRepository.save(comune);
    }

    // Elimina comune
    public void deleteComune(Long id) {
        Comune comune = findComuneById(id);
        comuneRepository.delete(comune);
    }

    // comune per nome e provincia
    public Comune findComuneByNomeAndProvincia(String nome, Provincia provincia) {
        return comuneRepository.findByNomeAndProvincia(nome, provincia)
                .orElseThrow(() -> new EntityNotFoundException("Comune non trovato"));
    }

   // comune esiste per nome
    public boolean existsByNome(String nome) {
        return comuneRepository.existsByNome(nome);
    }
}
