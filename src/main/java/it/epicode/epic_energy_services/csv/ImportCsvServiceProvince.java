package it.epicode.epic_energy_services.csv;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.exceptions.CsvValidationException;
import it.epicode.epic_energy_services.comuni.Comune;
import it.epicode.epic_energy_services.province.Provincia;
import it.epicode.epic_energy_services.province.ProvinciaRepository;
import it.epicode.epic_energy_services.province.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ImportCsvServiceProvince {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public Integer importProvince(MultipartFile file) throws IOException {
        Set<Provincia> province = parseCsv(file);
        List<Provincia> savedProvince = provinciaRepository.saveAll(province);
            return province.size();
    }

    private Set<Provincia> parseCsv(MultipartFile file) throws IOException {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<ProvinciaCsvRepresentation> strategy =
                    new HeaderColumnNameMappingStrategy<>();
            strategy.setType(ProvinciaCsvRepresentation.class);
            CsvToBean<ProvinciaCsvRepresentation> csvToBean = new CsvToBeanBuilder<ProvinciaCsvRepresentation>(reader)
                    .withMappingStrategy(strategy)
                    .withSeparator(';')
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

         return csvToBean.parse()
            .stream().map(csvLine -> Provincia.builder()
                    .sigla(csvLine.getSigla())
                    .nome(csvLine.getProvinciaImp())
                    .build()
                    )
                    .collect(Collectors.toSet());
        }
    }

}
