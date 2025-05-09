package it.epicode.epic_energy_services.fatture;

import it.epicode.epic_energy_services.clienti.Cliente;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "fatture")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fattura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "importo", nullable = false)
    private BigDecimal importo;

    @Column(name = "numero", nullable = false, unique = true)
    private String numero;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stato_id")
    private StatoFattura stato;
}