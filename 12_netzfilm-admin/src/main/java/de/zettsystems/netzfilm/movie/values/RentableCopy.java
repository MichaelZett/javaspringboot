package de.zettsystems.netzfilm.movie.values;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@ToString
@RequiredArgsConstructor
public class RentableCopy {
    @NotNull
    private final UUID uuid;
    private final String title;

}
