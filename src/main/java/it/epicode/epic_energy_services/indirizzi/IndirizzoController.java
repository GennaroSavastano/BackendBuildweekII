package it.epicode.epic_energy_services.indirizzi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/indirizzi")
@PreAuthorize("isAuthenticated()")
public class IndirizzoController {

    @Autowired
    private IndirizzoService indirizzoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<Indirizzo>> getAllIndirizzi(@PageableDefault(size = 20, sort = "via") Pageable pageable) {
        Page<Indirizzo> indirizzi = indirizzoService.findAllIndirizzi(pageable);
        return ResponseEntity.ok(indirizzi);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Indirizzo> getIndirizzoById(@PathVariable Long id) {
        Indirizzo indirizzo = indirizzoService.findIndirizzoById(id);
        return ResponseEntity.ok(indirizzo);
    }

}
