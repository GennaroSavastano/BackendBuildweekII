package it.epicode.epic_energy_services.utenti;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ruoli")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ruolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;
}