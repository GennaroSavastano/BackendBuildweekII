package it.epicode.epic_energy_services.clienti.service;

import it.epicode.epic_energy_services.clienti.Cliente;
import it.epicode.epic_energy_services.clienti.request.ClienteRequest;
import it.epicode.epic_energy_services.clienti.response.ClienteResponse;
import it.epicode.epic_energy_services.clienti.repository.ClienteRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Service
@Validated
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Page<ClienteResponse> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable).map(this::fromEntity);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente save(@Valid ClienteRequest request){
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(request, cliente);
        return clienteRepository.save(cliente);
    }

    public Cliente update(Long id, @Valid ClienteRequest request) {
            Cliente cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Cliente non trovato"));
            BeanUtils.copyProperties(request, cliente);
            return clienteRepository.save(cliente);
    }

    public ClienteResponse fromEntity(Cliente cliente) {
        ClienteResponse response = new ClienteResponse();
        BeanUtils.copyProperties(cliente, response);
        return response;
    }

    public Page<ClienteResponse> getAllClientiOrdinatiByNomeContatto( Pageable pageable) {
        return clienteRepository.findAllByOrderByNomeContattoAsc(pageable).map(this::fromEntity);
    }

    public Page<ClienteResponse> getAllClientiOrdinatiByFatturatoAnnuale(Pageable pageable) {
        return clienteRepository.findAllByOrderByFatturatoAnnualeAsc(pageable).map(this::fromEntity);
    }

    public Page<ClienteResponse> getAllClientiOrdinatiByDataInserimento(Pageable pageable) {
        return clienteRepository.findAllByOrderByDataInserimentoAsc(pageable).map(this::fromEntity);
    }

    public Page<ClienteResponse> getAllClientiOrdinatiByDataUltimoContatto(Pageable pageable) {
        return clienteRepository.findAllByOrderByDataUltimoContattoAsc(pageable).map(this::fromEntity);
    }

    public Page<ClienteResponse> findByFatturatoAnnuale(int min, int max, Pageable pageable) {
        return clienteRepository.findByFatturatoAnnualeBetween(min, max, pageable).map(this::fromEntity);
    }

    public Page<ClienteResponse> findByDataInserimento(LocalDate data, Pageable pageable) {
        return clienteRepository.findByDataInserimentoGreaterThanEqual(data, pageable).map(this::fromEntity);
    }

    public Page<ClienteResponse> findByDataUltimoContatto(LocalDate data, Pageable pageable) {
        return clienteRepository.findByDataUltimoContattoGreaterThanEqual(data, pageable).map(this::fromEntity);
    }

    public Page<ClienteResponse> findByPartNomeContatto(String nome, Pageable pageable) {
        return clienteRepository.findByNomeContattoContainingIgnoreCase(nome, pageable).map(this::fromEntity);
    }

    public Page<ClienteResponse> findByPartCognomeContatto(String cognome, Pageable pageable) {
        return clienteRepository.findByCognomeContattoContainingIgnoreCase(cognome, pageable).map(this::fromEntity);
    }
}
