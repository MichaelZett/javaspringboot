package de.zettsystems.netzfilm.customer.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@ToString
public class Customer {

    @Id
    @GeneratedValue
    @NotNull
    private long id;
    @Version
    @NotNull
    @Column(nullable = false)
    private long version;
    @NotNull
    @Column(unique = true, updatable = false, nullable = false)
    private UUID uuid;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotNull
    private LocalDate birthdate;

    //needed for jpa
    protected Customer() {
        super();
        this.uuid = UUID.randomUUID();
    }

    public Customer(String name, String lastName, LocalDate birthdate) {
        this();
        updateData(name, lastName, birthdate);
    }

    public void updateData(String name, String lastName, LocalDate birthdate) {
        this.name = name;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }
}
