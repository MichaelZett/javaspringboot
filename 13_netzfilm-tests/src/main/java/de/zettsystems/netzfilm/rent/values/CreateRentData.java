package de.zettsystems.netzfilm.rent.values;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
@ToString
public class CreateRentData {
    @NotNull
    private UUID copyUuid;
    @NotNull
    private UUID customerUuid;
    @FutureOrPresent
    private LocalDate startDate;
    @NotNull
    private Long days;

    public CreateRentData() {
        super();
    }
}
