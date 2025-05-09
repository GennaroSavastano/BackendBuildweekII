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

        @CsvBindByName(column = "Codice Provincia (Storico)(1)")
        private String codice;
        @CsvBindByName(column = "Progressivo del Comune (2)")
        private String progressivo;
        @CsvBindByName(column = "Denominazione in italiano")
        private String nome;
        @CsvBindByName(column = "Provincia")
        private String provincia;
    }

