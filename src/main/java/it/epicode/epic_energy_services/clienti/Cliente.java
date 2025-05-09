package it.epicode.epic_energy_services.clienti;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.epicode.epic_energy_services.fatture.Fattura;
import it.epicode.epic_energy_services.indirizzi.Indirizzo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "indirizzo_sede_legale")
    private Indirizzo indirizzoSedeLegale;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "indirizzo_sede_operativa")
    private Indirizzo indirizzoSedeOperativa;

    @OneToMany(mappedBy = "cliente")
    private List<Fattura> fatture = new ArrayList<>();
}
