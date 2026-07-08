package com.dariaromanowska.lab10.bootstrap;

import com.dariaromanowska.lab10.domain.Location;
import com.dariaromanowska.lab10.domain.Measurement;
import com.dariaromanowska.lab10.repository.LocationRepository;
import com.dariaromanowska.lab10.repository.MeasurementRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final LocationRepository locationRepository;
    private final MeasurementRepository measurementRepository;

    public DataLoader(LocationRepository locationRepository, MeasurementRepository measurementRepository) {
        this.locationRepository = locationRepository;
        this.measurementRepository = measurementRepository;
    }

    @Override
    public void run(String... args) {
        if (locationRepository.count() > 0 || measurementRepository.count() > 0) {
            return;
        }

        Location gdansk = new Location();
        gdansk.setCity("Gdansk");
        gdansk.setCountry("PL");
        gdansk.setLatitude(new BigDecimal("54.3520"));
        gdansk.setLongitude(new BigDecimal("18.6466"));

        Location berlin = new Location();
        berlin.setCity("Berlin");
        berlin.setCountry("DE");
        berlin.setLatitude(new BigDecimal("52.5200"));
        berlin.setLongitude(new BigDecimal("13.4050"));

        Location paris = new Location();
        paris.setCity("Paris");
        paris.setCountry("FR");
        paris.setLatitude(new BigDecimal("48.8566"));
        paris.setLongitude(new BigDecimal("2.3522"));

        locationRepository.save(gdansk);
        locationRepository.save(berlin);
        locationRepository.save(paris);

        measurementRepository.save(buildMeasurement(gdansk, LocalDate.now().minusDays(2), 7, 82));
        measurementRepository.save(buildMeasurement(gdansk, LocalDate.now().minusDays(1), 9, 74));
        measurementRepository.save(buildMeasurement(gdansk, LocalDate.now(), 6, 88));

        measurementRepository.save(buildMeasurement(berlin, LocalDate.now().minusDays(2), 10, 65));
        measurementRepository.save(buildMeasurement(berlin, LocalDate.now().minusDays(1), 12, 60));
        measurementRepository.save(buildMeasurement(berlin, LocalDate.now(), 11, 58));

        measurementRepository.save(buildMeasurement(paris, LocalDate.now().minusDays(2), 13, 70));
        measurementRepository.save(buildMeasurement(paris, LocalDate.now().minusDays(1), 15, 64));
        measurementRepository.save(buildMeasurement(paris, LocalDate.now(), 14, 62));
    }

    private Measurement buildMeasurement(Location location, LocalDate date, int celsius, int humidity) {
        Measurement measurement = new Measurement();
        measurement.setLocation(location);
        measurement.setDate(date);
        measurement.setCelsius(celsius);
        measurement.setHumidity(humidity);
        return measurement;
    }
}
