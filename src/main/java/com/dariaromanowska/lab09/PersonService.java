package com.dariaromanowska.lab09;

import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final List<Person> people = new ArrayList<>();

    @PostConstruct
    public void init() {
        people.add(new Person("Adam", "Mada"));
        people.add(new Person("Eryk", "Kyre"));
    }

    public List<Person> getPeople() {
        return people;
    }

    public Person getPerson(int index) {
        return people.get(index);
    }

    public Person addPerson(Person person) {
        people.add(person);
        return person;
    }

    public Person setPerson(int index, Person person) {
        people.set(index, person);
        return person;
    }

    public Person removePerson(int index) {
        return people.remove(index);
    }
}
