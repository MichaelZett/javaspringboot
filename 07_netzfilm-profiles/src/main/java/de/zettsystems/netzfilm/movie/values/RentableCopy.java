package de.zettsystems.netzfilm.movie.values;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@RequiredArgsConstructor
public class RentableCopy {
    private final UUID uuid;
    private final String title;

}
