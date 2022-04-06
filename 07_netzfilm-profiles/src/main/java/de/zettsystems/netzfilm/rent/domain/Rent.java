package de.zettsystems.netzfilm.rent.domain;

import de.zettsystems.netzfilm.customer.domain.Customer;
import de.zettsystems.netzfilm.movie.domain.Copy;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@ToString
public class Rent {
    @Id
    @GeneratedValue
    private long id;
    @Version
    private long version;
    private UUID uuid;
    @OneToOne
    private Copy copy;
    @OneToOne
    private Customer customer;
    private BigDecimal amount;
    private LocalDate start;
    private LocalDate end;

    //needed for jpa
    protected Rent() {
        super();
        this.uuid = UUID.randomUUID();
    }

    public Rent(Copy copy, Customer customer, BigDecimal amount, LocalDate start, LocalDate end) {
        this();
        this.copy = copy;
        this.customer = customer;
        this.amount = amount;
        this.start = start;
        this.end = end;
    }

}
