package it.epicode.epic_energy_services.comuni;

import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/comuni")
@PreAuthorize("isAuthenticated()")
public class ComuneController {


@Autowired
private ComuneService comuneService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<Comune>> getAllComuni(@PageableDefault(size = 20, sort = "nome") Pageable pageable) {
        Page<Comune> comuni = comuneService.findAllComuni(pageable);
        return ResponseEntity.ok(comuni);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Comune> getComuneById(@PathVariable Long id) {
        Comune comune = comuneService.findComuneById(id);
        return ResponseEntity.ok(comune);
    }

    @GetMapping("/nome/{nome}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Comune> getComuneByNome(@PathVariable String nome) {
        Comune comune = comuneService.findComuneByNome(nome);
        return ResponseEntity.ok(comune);
    }

}
