package it.epicode.epic_energy_services.clienti.request;

import it.epicode.epic_energy_services.clienti.RagioneSociale;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {
    @NotNull(message = "Ragione Sociale is mandatory")
    private RagioneSociale ragioneSociale;

    @NotBlank(message = "Partita Iva is mandatory")
    @Size(min = 11, max = 11, message = "La partita iva deve avere 11 caratteri")
    private String partitaIva;

    @NotNull(message = "Email is mandatory")
    @Email(message = "Formato email non valido")
    private String email;

    @NotNull(message = "Data Inserimento is mandatory")
    private LocalDate dataInserimento;

    @NotNull(message = "Data Ultimo Contatto is mandatory")
    private LocalDate dataUltimoContatto;

    @NotNull(message = "Fatturato Annuale is mandatory")
    private int fatturatoAnnuale;

    @NotNull(message = "Pec is mandatory")
    @Email(message = "Formato email non valido")
    private String pec;

    @NotBlank(message = "Telefono is mandatory")
    @Size(min = 10, max = 10, message = "Il numero di telefono deve avere 10 caratteri")
    private String telefono;

    @NotNull(message = "Email Contatto is mandatory")
    @Email(message = "Formato email non valido")
    private String emailContatto;

    @NotBlank(message = "Nome Contatto is mandatory")
    @Size(min = 1, max = 100, message = "Il nome contatto deve avere tra 1 e 100 caratteri")
    private String nomeContatto;

    @NotBlank(message = "Cognome Contatto is mandatory")
    @Size(min = 1, max = 100, message = "Il cognome contatto deve avere tra 1 e 100 caratteri")
    private String cognomeContatto;

    @NotBlank(message = "Telefono Contatto is mandatory")
    @Size(min = 10, max = 10, message = "Il telefono deve avere 10 caratteri")
    private String telefonoContatto;
}
