package it.epicode.epic_energy_services.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProvinciaCsvRepresentation {

    @CsvBindByName(column = "sigla")
    private String sigla;
    @CsvBindByName(column = "provincia_imp")
    private String provinciaImp;
    @CsvBindByName(column = "regione")
    private String regione;
}
