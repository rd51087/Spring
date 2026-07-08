package com.dariaromanowska.lab13.controller;

import com.dariaromanowska.lab13.domain.Address;
import com.dariaromanowska.lab13.domain.Person;
import com.dariaromanowska.lab13.repository.PersonRepository;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/people")
@CrossOrigin(origins = "http://localhost:4200")
public class PeopleController {

    private final PersonRepository personRepository;

    public PeopleController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable Long id) {
        return personRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody(required = false) Person person) {
        if (person == null) {
            return ResponseEntity.badRequest().build();
        }
        person.setId(null);
        ensureAddress(person);
        Person saved = personRepository.save(person);
        return ResponseEntity.created(URI.create("/api/people/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody(required = false) Person person) {
        if (person == null) {
            return ResponseEntity.badRequest().build();
        }
        if (person.getId() != null && !person.getId().equals(id)) {
            return ResponseEntity.badRequest().build();
        }
        if (!personRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ensureAddress(person);
        person.setId(id);
        Person saved = personRepository.save(person);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!personRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        personRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private void ensureAddress(Person person) {
        if (person.getAddress() == null) {
            person.setAddress(new Address());
        }
    }
}
