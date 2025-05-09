package it.epicode.epic_energy_services.clienti.controller;

import it.epicode.epic_energy_services.clienti.Cliente;
import it.epicode.epic_energy_services.clienti.request.ClienteRequest;
import it.epicode.epic_energy_services.clienti.response.ClienteResponse;
import it.epicode.epic_energy_services.clienti.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class ClienteController {
    private final ClienteService clienteService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public Page<ClienteResponse> findAll(@ParameterObject Pageable pageable) {
        return clienteService.findAll(pageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Valid ClienteRequest request) {
        return clienteService.save(request);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public Cliente update(@PathVariable Long id, @RequestBody @Valid ClienteRequest request) {
        return clienteService.update(id, request);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        clienteService.deleteById(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/nomeContatto")
    public Page<ClienteResponse> orderClientiByNome(@ParameterObject Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("nomeContatto").ascending());
        return clienteService.getAllClientiOrdinatiByNomeContatto(sortedPageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/fatturatoAnnuale")
    public Page<ClienteResponse> orderClientiByFatturatoAnnuale(@ParameterObject Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("fatturatoAnnuale").ascending());
        return clienteService.getAllClientiOrdinatiByFatturatoAnnuale(sortedPageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/dataInserimento")
    public Page<ClienteResponse> orderClientiByDataInserimento(@ParameterObject Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("dataInserimento").ascending());
        return clienteService.getAllClientiOrdinatiByDataInserimento(sortedPageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/dataUltimoContatto")
    public Page<ClienteResponse> orderClientiByDataUltimoContatto(@ParameterObject Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("dataUltimoContatto").ascending());
        return clienteService.getAllClientiOrdinatiByDataUltimoContatto(sortedPageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/provinciaSedeLegale")
    public Page<ClienteResponse> orderClientiByProvinciaSedeLegale(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return clienteService.getClientiOrdinatiByProvincia(page, size);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/filterFatturato")
    public Page<ClienteResponse> filterByFatturatoAnnuale(@RequestParam int min, @RequestParam int max, @ParameterObject Pageable pageable) {
        return clienteService.findByFatturatoAnnuale(min, max, pageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/filterDataInserimento")
    public Page<ClienteResponse> filterByDataInserimento(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data, @ParameterObject Pageable pageable) {
        return clienteService.findByDataInserimento(data, pageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/filterDataUltimoContatto")
    public Page<ClienteResponse> filterByDataUltimoContatto(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data, @ParameterObject Pageable pageable) {
        return clienteService.findByDataUltimoContatto(data, pageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/filterNomeContatto")
    public Page<ClienteResponse> filterByPartNomeContatto(@RequestParam String nome, @ParameterObject Pageable pageable) {
        return clienteService.findByPartNomeContatto(nome, pageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/filterCognomeContatto")
    public Page<ClienteResponse> filterByPartCognomeContatto(@RequestParam String cognome, @ParameterObject Pageable pageable) {
        return clienteService.findByPartCognomeContatto(cognome, pageable);
    }
 }
