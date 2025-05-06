package it.epicode.epic_energy_services.province;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaService {
    @Autowired
    private ProvinciaRepository provinciaRepository;

    public List<Provincia> findAllProvince() {
        return provinciaRepository.findAll();
    }

    // provincia per id
    public Provincia findProvinciaById(Long id) {
        return provinciaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provincia non trovata"));
    }
    public Provincia findProvinciaBySigla(String sigla) {
        return provinciaRepository.findBySigla(sigla)
                .orElseThrow(() -> new EntityNotFoundException("Provincia non trovata"));
    }
    public Provincia saveProvincia(Provincia provincia) {
        if (provincia.getNome() == null || provincia.getSigla() == null) {
            throw new IllegalArgumentException("Nome e sigla non possono essere nulli");
        }
        if (provinciaRepository.existsBySigla(provincia.getSigla())) {
            throw new EntityExistsException("Provincia già esistente");
        }
        return provinciaRepository.save(provincia);
    }

    public Provincia updateProvincia(Long id, Provincia provincia) {
        if (provincia.getNome() == null || provincia.getSigla() == null) {
            throw new IllegalArgumentException("Nome e sigla non possono essere nulli");
        }
        Provincia existingProvincia = provinciaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provincia non trovata"));
        if (!existingProvincia.getSigla().equals(provincia.getSigla()) && provinciaRepository.existsBySigla(provincia.getSigla())) {
            throw new EntityExistsException("Provincia già esistente");
        }
        existingProvincia.setNome(provincia.getNome());
        existingProvincia.setSigla(provincia.getSigla());
        return provinciaRepository.save(existingProvincia);
    }

    public void deleteProvincia(Long id) {
        Provincia provincia = provinciaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provincia non trovata"));
        provinciaRepository.delete(provincia);
    }

    public boolean existsBySigla(String sigla) {
        return provinciaRepository.existsBySigla(sigla);
    }
}

