package de.zettsystems.netzfilm.movie.domain;


import lombok.Getter;
import lombok.ToString;


import java.time.LocalDate;
import java.util.UUID;

@Getter
@ToString
public class Movie {
    private final long id;
    private final UUID uuid;
    private String title;
    private LocalDate releaseDate;

    public Movie(long id, String title, LocalDate releaseDate) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.uuid = UUID.randomUUID();
    }
}
