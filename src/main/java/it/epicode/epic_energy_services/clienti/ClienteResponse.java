package it.epicode.epic_energy_services.clienti;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {
    private Long id;
    private RagioneSociale ragioneSociale;
    private String partitaIva;
    private Email email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private int fatturatoAnnuale;
    private String pec;
    private String telefono;
    private Email emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private String telefonoContatto;
}
