package de.zettsystems.netzfilm.movie.values;


import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;


@Getter
public class MovieData extends CreateMovieData {
    @NotNull
    private UUID uuid;
    @NotNull
    private Long version;

    protected MovieData() {
        super();
        this.uuid = UUID.randomUUID();
    }

    public MovieData(UUID uuid, String title, LocalDate releaseDate) {
        super(title, releaseDate);
        this.uuid = uuid;
    }

    public MovieData(UUID uuid, String title, LocalDate releaseDate, long version) {
        this(uuid, title, releaseDate);
        this.version = version;
    }
}
