package de.zettsystems.order.movie.adapter;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@ToString
@Getter
public class MovieDbEntry {
    private UUID uuid;
    private String title;
    private LocalDate releaseDate;

    public MovieDbEntry() {
        super();
        this.uuid = UUID.randomUUID();
    }

    public MovieDbEntry(String title, LocalDate releaseDate) {
        this();
        this.title = title;
        this.releaseDate = releaseDate;
    }

}
