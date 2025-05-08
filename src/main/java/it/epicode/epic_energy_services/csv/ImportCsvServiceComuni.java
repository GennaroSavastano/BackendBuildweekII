package it.epicode.epic_energy_services.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import it.epicode.epic_energy_services.comuni.Comune;
import it.epicode.epic_energy_services.comuni.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ImportCsvServiceComuni {


        @Autowired
        private ComuneRepository comuneRepository;

        public Integer importComuni(MultipartFile file) throws IOException {
            Set<Comune> comuni = parseCsv(file);
            return null;
        }

        private Set<Comune> parseCsv(MultipartFile file) throws IOException {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                HeaderColumnNameMappingStrategy<ComuneCsvRepresentation> strategy =
                        new HeaderColumnNameMappingStrategy<>();
                strategy.setType(ComuneCsvRepresentation.class);
                CsvToBean<ComuneCsvRepresentation> csvToBean = new CsvToBeanBuilder<ComuneCsvRepresentation>(reader)
                        .withMappingStrategy(strategy)
                        .withSeparator(';')
                        .withIgnoreEmptyLine(true)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                return csvToBean.parse()
                        .stream().map(csvLine -> Comune.builder()
                                .nome(csvLine.getNome())
                                .build()
                        )
                        .collect(Collectors.toSet());
            }
        }

    }

