package it.epicode.epic_energy_services.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.InputStream;

@Component
@Order(1)
@RequiredArgsConstructor
public class CsvImportRunner implements CommandLineRunner {
    private final ImportCsvServiceComuni importCsvServiceComuni;
    private final ImportCsvServiceProvince importCsvServiceProvince;

    @Override
    public void run(String... args) throws Exception {

        try {
            // Import province prima (così le comuni possono collegarsi dopo)
            try (InputStream provinceStream = getClass().getClassLoader().getResourceAsStream("province-italiane.csv")) {
                if (provinceStream == null) {
                    System.err.println("❌ File province.csv non trovato in resources!");
                } else {
                    Integer imported = importCsvServiceProvince.importProvinceFromResource(provinceStream);
                    System.out.println("✅ Province importate: " + imported);
                }
            }

            // Import comuni poi
            try (InputStream comuniStream = getClass().getClassLoader().getResourceAsStream("comuni-italiani.csv")) {
                if (comuniStream == null) {
                    System.err.println("❌ File comuni.csv non trovato in resources!");
                } else {
                    Integer imported = importCsvServiceComuni.importComuniFromResource(comuniStream);
                    System.out.println("✅ Comuni importati: " + imported);
                }
            }

        } catch (IOException e) {
            System.err.println("❌ Errore durante l'importazione: " + e.getMessage());
        }
    }


    }
