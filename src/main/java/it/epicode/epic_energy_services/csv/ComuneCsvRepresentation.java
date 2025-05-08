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
    public class ComuneCsvRepresentation {

        @CsvBindByName(column = "codice provincia")
        private String codice;
        @CsvBindByName(column = "progressivo comune")
        private String progressivo;
        @CsvBindByName(column = "nome comune")
        private String nome;
        @CsvBindByName(column = "provincia")
        private String provincia;
    }

