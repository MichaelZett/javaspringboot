package de.zettsystems.netzfilm.customer.values;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreateCustomerData {
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotNull
    private LocalDate birthdate;

    public CreateCustomerData() {
        super();
    }

}
