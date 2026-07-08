package com.dariaromanowska.lab10.repository;

import com.dariaromanowska.lab10.domain.Location;
import com.dariaromanowska.lab10.domain.Measurement;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

@RepositoryRestResource
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    Page<Measurement> findByDateGreaterThanEqualAndLocation(
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
        Location location,
        Pageable pageable
    );
}
