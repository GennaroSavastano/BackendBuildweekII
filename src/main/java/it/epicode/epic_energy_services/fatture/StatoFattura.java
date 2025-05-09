package it.epicode.epic_energy_services.fatture;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stati_fattura")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatoFattura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;
}