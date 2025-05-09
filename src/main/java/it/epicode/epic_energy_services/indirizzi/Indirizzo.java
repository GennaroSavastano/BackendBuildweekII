package it.epicode.epic_energy_services.indirizzi;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.epicode.epic_energy_services.clienti.Cliente;
import it.epicode.epic_energy_services.comuni.Comune;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String via;
    @Column(nullable = false)
    private String civico;
    private String localita;
    @Column(nullable = false)
    private String cap;

    @ManyToOne(optional = false)
    @JoinColumn(name = "comune_id")
    private Comune comune;

    @JsonBackReference
    @OneToOne(mappedBy = "indirizzoSedeLegale")
    private Cliente clienteSedeLegale;

    @JsonBackReference
    @OneToOne(mappedBy = "indirizzoSedeOperativa")
    private Cliente clienteSedeOperativa;

}
