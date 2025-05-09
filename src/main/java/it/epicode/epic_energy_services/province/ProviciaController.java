package it.epicode.epic_energy_services.province;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/province")
@PreAuthorize("isAuthenticated()")
public class ProviciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<Provincia>> getAllProvince() {
        return ResponseEntity.ok(provinciaService.findAllProvince());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Provincia> getProvinciaById(@PathVariable Long id) {
        Provincia provincia = provinciaService.findProvinciaById(id);
        return ResponseEntity.ok(provincia);
    }

    @GetMapping("/sigla/{sigla}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Provincia> getProvinciaBySigla(@PathVariable String sigla) {
        Provincia provincia = provinciaService.findProvinciaBySigla(sigla);
        return ResponseEntity.ok(provincia);
    }
}
