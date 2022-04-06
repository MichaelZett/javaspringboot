package de.zettsystems.netzfilm.customer.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@ToString
public class Customer {

    @Id
    @GeneratedValue
    private long id;
    @Version
    private long version;
    private UUID uuid;
    private String name;
    private String lastName;
    private LocalDate birthdate;

    //needed for jpa
    protected Customer() {
        super();
        this.uuid = UUID.randomUUID();
    }

    public Customer(String name, String lastName, LocalDate birthdate) {
        this();
        this.name = name;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }
}
