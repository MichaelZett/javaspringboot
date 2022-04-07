package de.zettsystems.netzfilm.movie.values;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class MovieDbEntry {
    private UUID uuid;
    private String title;
    private LocalDate releaseDate;

}
