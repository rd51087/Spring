package com.dariaromanowska.lab13.bootstrap;

import com.dariaromanowska.lab13.domain.Address;
import com.dariaromanowska.lab13.domain.Person;
import com.dariaromanowska.lab13.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final PersonRepository personRepository;

    public DataLoader(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) {
        if (personRepository.count() > 0) {
            return;
        }

        personRepository.save(buildPerson("Anna", "Kowalska", 28, "Gdansk", "Dluga 12", "80-831"));
        personRepository.save(buildPerson("Pawel", "Nowak", 35, "Warszawa", "Marszalkowska 21", "00-590"));
        personRepository.save(buildPerson("Maria", "Zielinska", 42, "Krakow", "Grodzka 8", "31-006"));
    }

    private Person buildPerson(String firstName, String familyName, int age, String city, String street, String postCode) {
        Address address = new Address();
        address.setCity(city);
        address.setStreet(street);
        address.setPostCode(postCode);

        Person person = new Person();
        person.setFirstName(firstName);
        person.setFamilyName(familyName);
        person.setAge(age);
        person.setAddress(address);
        return person;
    }
}
