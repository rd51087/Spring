package com.dariaromanowska.lab10.repository;

import com.dariaromanowska.lab10.domain.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LocationRepository extends JpaRepository<Location, Long> {

    Page<Location> findByCountryIgnoreCase(String country, Pageable pageable);

    Location findByCityIgnoreCase(String city);
}
