package it.epicode.epic_energy_services.clienti;

import com.github.javafaker.Faker;
import it.epicode.epic_energy_services.clienti.repository.ClienteRepository;
import it.epicode.epic_energy_services.comuni.Comune;
import it.epicode.epic_energy_services.comuni.ComuneRepository;
import it.epicode.epic_energy_services.indirizzi.Indirizzo;
import it.epicode.epic_energy_services.indirizzi.IndirizzoRepository;
import it.epicode.epic_energy_services.province.Provincia;
import it.epicode.epic_energy_services.province.ProvinciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Order(2)
@RequiredArgsConstructor
public class ClienteRunner implements CommandLineRunner {
    private final ClienteRepository clienteRepository;
    private final Faker faker;
    private final ComuneRepository comuneRepository;
    private final IndirizzoRepository indirizzoRepository;
    private final ProvinciaRepository provinciaRepository;


    @Override
    public void run(String... args) throws Exception {
        if(clienteRepository.count() == 0) {
            Random random = new Random();
            List<Comune> comuni = comuneRepository.findAll();
            List<Provincia> pronvince = provinciaRepository.findAll();

            for(int i = 0; i < 10; i++) {
                Cliente cliente = new Cliente();
                cliente.setRagioneSociale(RagioneSociale.values()[random.nextInt(RagioneSociale.values().length)]);
                cliente.setPartitaIva(faker.number().digits(11));
                cliente.setEmail(faker.internet().emailAddress());

                LocalDate randomDateInserimento = getRandomDateFrom2024();
                LocalDate randomDateContatto = getRandomDateFrom2025();
                cliente.setDataInserimento(randomDateInserimento);
                cliente.setDataUltimoContatto(randomDateContatto);
                cliente.setFatturatoAnnuale(random.nextInt(10000000));
                cliente.setPec(faker.internet().emailAddress());
                cliente.setTelefono(faker.phoneNumber().cellPhone());
                cliente.setEmailContatto(faker.internet().emailAddress());
                cliente.setNomeContatto(faker.name().firstName());
                cliente.setCognomeContatto(faker.name().lastName());
                cliente.setTelefonoContatto(faker.phoneNumber().cellPhone());

                Comune comuneLegale = comuni.get(random.nextInt(comuni.size()));
                Indirizzo indirizzoLegale = new Indirizzo();
                indirizzoLegale.setVia(faker.address().streetName());
                indirizzoLegale.setCivico(faker.address().buildingNumber());
                indirizzoLegale.setCap(faker.address().zipCode());
                indirizzoLegale.setComune(comuneLegale);
                indirizzoRepository.save(indirizzoLegale);
                cliente.setIndirizzoSedeLegale(indirizzoLegale);

                Comune comuneOperativa = comuni.get(random.nextInt(comuni.size()));
                Indirizzo indirizzoOperativa = new Indirizzo();
                indirizzoOperativa.setVia(faker.address().streetName());
                indirizzoOperativa.setCivico(faker.address().buildingNumber());
                indirizzoOperativa.setCap(faker.address().zipCode());
                indirizzoOperativa.setComune(comuneOperativa);
                indirizzoRepository.save(indirizzoOperativa);
                cliente.setIndirizzoSedeOperativa(indirizzoOperativa);

                clienteRepository.save(cliente);
            }
        }

    }
    private LocalDate getRandomDateFrom2024() {
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024,12,31);
        long days = ChronoUnit.DAYS.between(start, end);
        return start.plusDays(ThreadLocalRandom.current().nextLong(days + 1));
    }
    private LocalDate getRandomDateFrom2025() {
        LocalDate start = LocalDate.of(2025, 1, 1);
        LocalDate end = LocalDate.now();
        long days = ChronoUnit.DAYS.between(start, end);
        return start.plusDays(ThreadLocalRandom.current().nextLong(days + 1));
    }
}
