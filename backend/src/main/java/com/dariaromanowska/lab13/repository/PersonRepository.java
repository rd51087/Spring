package com.dariaromanowska.lab13.repository;

import com.dariaromanowska.lab13.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
