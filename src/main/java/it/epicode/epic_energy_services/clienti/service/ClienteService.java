package it.epicode.epic_energy_services.clienti.service;

import it.epicode.epic_energy_services.clienti.Cliente;
import it.epicode.epic_energy_services.clienti.request.ClienteRequest;
import it.epicode.epic_energy_services.clienti.response.ClienteResponse;
import it.epicode.epic_energy_services.clienti.repository.ClienteRepository;
import it.epicode.epic_energy_services.indirizzi.Indirizzo;
import it.epicode.epic_energy_services.indirizzi.IndirizzoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Service
@Validated
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final IndirizzoRepository indirizzoRepository;

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Page<ClienteResponse> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable).map(this::fromEntity);
    }

    public void deleteById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        Indirizzo indirizzoSedeLegale = cliente.getIndirizzoSedeLegale();
        Indirizzo indirizzoSedeOperativa = cliente.getIndirizzoSedeOperativa();
        indirizzoSedeOperativa.setClienteSedeOperativa(null);
        indirizzoSedeLegale.setClienteSedeLegale(null);
        indirizzoRepository.findByClienteId(id).forEach(indirizzoRepository::delete);
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

    public Page<ClienteResponse> getClientiOrdinatiByProvincia(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return clienteRepository.findAllByOrderByIndirizzoSedeLegale_Comune_ProvinciaDaCsvAsc(pageable).map(this::fromEntity);
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
