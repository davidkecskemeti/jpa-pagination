package com.dk.jpa.pagination.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name = "Person.findByFirstNameNamed",
        query = "SELECT p FROM Person p WHERE p.firstName = ?1")
@NamedNativeQuery(name = "Person.findByLastNameNativeNamed",
        query = "SELECT * FROM Person p WHERE p.lastName = :lastName")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
