package it.epicode.epic_energy_services.csv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/import")
public class ImportCsvController {

    @Autowired
    private ImportCsvServiceProvince importCsvServiceProvince;

    @Autowired
    private ImportCsvServiceComuni importCsvServiceComuni;

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/province", consumes = "multipart/form-data")
    public ResponseEntity<Integer> importProvince(@RequestParam("file")MultipartFile file) throws IOException {
        return ResponseEntity.ok( importCsvServiceProvince.importProvince(file));
    }
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/comuni", consumes = "multipart/form-data")
    public ResponseEntity<Integer> importComuni(@RequestParam("file")MultipartFile file) throws IOException {
        return ResponseEntity.ok(importCsvServiceComuni.importComuni(file));
    }
}
