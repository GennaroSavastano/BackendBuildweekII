package it.epicode.epic_energy_services.clienti;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RagioneSociale ragioneSociale;
    @Column(nullable = false)
    private String partitaIva;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private LocalDate dataInserimento;
    @Column(nullable = false)
    private LocalDate dataUltimoContatto;
    @Column(nullable = false)
    private int fatturatoAnnuale;
    @Column(nullable = false)
    private String pec;
    @Column(nullable = false)
    private String telefono;
    @Column(nullable = false)
    private String emailContatto;
    @Column(nullable = false)
    private String nomeContatto;
    @Column(nullable = false)
    private String cognomeContatto;
    @Column(nullable = false)
    private String telefonoContatto;
}
