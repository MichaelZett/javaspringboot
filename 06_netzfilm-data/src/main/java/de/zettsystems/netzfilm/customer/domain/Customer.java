package de.zettsystems.netzfilm.customer.domain;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@ToString
public class Customer {

    private long id;
    private UUID uuid;
    private String name;
    private String lastName;
    private LocalDate birthdate;

    public Customer(long id, String name, String lastName, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.uuid = UUID.randomUUID();
    }
}
