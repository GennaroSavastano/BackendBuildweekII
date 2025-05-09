package it.epicode.epic_energy_services.indirizzi;

import it.epicode.epic_energy_services.comuni.Comune;
import it.epicode.epic_energy_services.comuni.ComuneService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IndirizzoService {

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    @Autowired
    private ComuneService comuneService;

    // tutti gli indirizzi paginati
    public Page<Indirizzo> findAllIndirizzi(Pageable pageable) {
        return indirizzoRepository.findAll(pageable);
    }

    // indirizzo da id
    public Indirizzo findIndirizzoById(Long id) {
        return indirizzoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Indirizzo non trovato"));
    }

    // salva indirizzo
    public Indirizzo saveIndirizzo(Indirizzo indirizzo) {
        comuneService.findComuneById(indirizzo.getComune().getId());
        return indirizzoRepository.save(indirizzo);
    }

    // Aggiorna indirizzo esistente
    public Indirizzo updateIndirizzo(Long id, Indirizzo indirizzoModificato) {
        Indirizzo indirizzo = findIndirizzoById(id);
        indirizzo.setVia(indirizzoModificato.getVia());
        indirizzo.setCivico(indirizzoModificato.getCivico());
        indirizzo.setLocalita(indirizzoModificato.getLocalita());
        indirizzo.setCap(indirizzoModificato.getCap());
        // aggiorna il comune se fornito e valido
        if (indirizzoModificato.getComune() != null && indirizzoModificato.getComune().getId() != null) {
            Comune nuovoComune = comuneService.findComuneById(indirizzoModificato.getComune().getId());
            indirizzo.setComune(nuovoComune);
        }
        return indirizzoRepository.save(indirizzo);
    }
    // Elimina indirizzo
    public void deleteIndirizzo(Long id) {
        Indirizzo indirizzo = findIndirizzoById(id);
        indirizzoRepository.delete(indirizzo);
    }
}
