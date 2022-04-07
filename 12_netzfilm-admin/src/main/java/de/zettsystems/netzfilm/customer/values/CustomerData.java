package de.zettsystems.netzfilm.customer.values;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Getter
public class CustomerData extends CreateCustomerData {
    @NotNull
    private UUID uuid;
    @NotNull
    private Long version;

    public CustomerData() {
        super();
    }

    public CustomerData(UUID uuid, String name, String lastName, LocalDate birthdate) {
        super(name, lastName, birthdate);
        this.uuid = uuid;
    }

    public CustomerData(UUID uuid, String name, String lastName, LocalDate birthdate, long version) {
        this(uuid, name, lastName, birthdate);
        this.version = version;
    }

}
