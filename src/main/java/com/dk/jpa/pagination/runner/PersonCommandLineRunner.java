package com.dk.jpa.pagination.runner;

import com.dk.jpa.pagination.entity.Person;
import com.dk.jpa.pagination.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PersonCommandLineRunner implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        // create persons
        List<Person> personList = Arrays.asList(
                new Person("John", "Doe", 55),
                new Person("Emma", "Watson", 29),
                new Person("Jovan", "Hover", 35),
                new Person("Atta", "Shah", 30),
                new Person("Mike", "Lee", 61)
        );
        personRepository.saveAll(personList);

        // get all persons by last name
        Pageable pageable = PageRequest.of(0, 3);
        Page<Person> personPage = personRepository.findByLastName("Doe", pageable);

        // get all persons sorted by their age in the descending order
        Pageable pageable2 = PageRequest.of(0, 5, Sort.by("age").descending());
        Page<Person> personPage2 = personRepository.findAll(pageable2);

        // skip pagination
        Slice<Person> personSlice = personRepository.findByAgeBetween(20, 60, Pageable.unpaged());

    }
}
