package it.epicode.epic_energy_services.utenti;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "utenti")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    private String avatar;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "utenti_ruoli",
            joinColumns = @JoinColumn(name = "utente_id"),
            inverseJoinColumns = @JoinColumn(name = "ruolo_id")
    )
    private Set<Ruolo> ruoli;
}