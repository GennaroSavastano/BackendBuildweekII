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

    @CsvBindByName(column = "Sigla")
    private String sigla;
    @CsvBindByName(column = "Provincia")
    private String provinciaImp;
    @CsvBindByName(column = "Regione")
    private String regione;
}
