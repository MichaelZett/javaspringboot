package de.zettsystems.netzfilm.rent.domain;

import de.zettsystems.netzfilm.customer.domain.Customer;
import de.zettsystems.netzfilm.movie.domain.Copy;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Entity
@Getter
@ToString
public class Rent {
    @Id
    @GeneratedValue
    @NotNull
    private long id;
    @NotNull
    @Version
    @Column(nullable = false)
    private long version;
    @NotNull
    @Column(unique = true, updatable = false, nullable = false)
    private UUID uuid;
    @ManyToOne
    @NotNull
    @Valid
    @JoinColumn(updatable = false, nullable = false)
    private Copy copy;
    @ManyToOne
    @NotNull
    @Valid
    @JoinColumn(updatable = false, nullable = false)
    private Customer customer;
    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private BigDecimal amount;
    @NotNull
    @Column(nullable = false)
    private LocalDate start;
    @NotNull
    @FutureOrPresent
    @Column(nullable = false)
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

    @AssertTrue(message = "We don't charge that much for children!")
    public boolean isCorrectAgeAmount() {
        final Period age = Period.between(customer.getBirthdate(), LocalDate.now());
        return age.getYears() > 18 || amount.compareTo(BigDecimal.ONE) < 0;
    }

}
