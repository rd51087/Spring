package com.dariaromanowska.lab09;

import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class PeopleRestController {
    private final PersonService personService;

    public PeopleRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getPeople() {
        return personService.getPeople();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable int id) {
        try {
            return ResponseEntity.ok(personService.getPerson(id));
        } catch (IndexOutOfBoundsException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        Person created = personService.addPerson(person);
        return ResponseEntity.created(URI.create("/api/people/" + (personService.getPeople().size() - 1)))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        try {
            return ResponseEntity.ok(personService.setPerson(id, person));
        } catch (IndexOutOfBoundsException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable int id) {
        try {
            personService.removePerson(id);
            return ResponseEntity.noContent().build();
        } catch (IndexOutOfBoundsException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
