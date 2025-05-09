package it.epicode.epic_energy_services.province;

import it.epicode.epic_energy_services.comuni.Comune;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String sigla;
    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "provincia")
    private List<Comune> comuni = new ArrayList<>();
}
