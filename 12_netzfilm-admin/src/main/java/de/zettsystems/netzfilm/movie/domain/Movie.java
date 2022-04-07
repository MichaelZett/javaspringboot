package de.zettsystems.netzfilm.movie.domain;


import de.zettsystems.netzfilm.movie.values.MovieDbEntry;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@ToString
public class Movie {
    @Id
    @GeneratedValue
    @NotNull
    private long id;
    @Version
    @NotNull
    @Column(nullable = false)
    private long version;
    @NotNull
    @Column(unique = true, updatable = false, nullable = false)
    private UUID uuid;
    @NotBlank
    @Column(nullable = false)
    private String title;
    @NotNull
    @Column(nullable = false)
    private LocalDate releaseDate;

    //needed for jpa
    protected Movie() {
        super();
        this.uuid = UUID.randomUUID();
    }

    public Movie(String title, LocalDate releaseDate) {
        this();
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public Movie(MovieDbEntry movieDbEntry) {
        this.uuid = movieDbEntry.getUuid();
        this.title = movieDbEntry.getTitle();
        this.releaseDate = movieDbEntry.getReleaseDate();
    }

    public void updateData(String title, LocalDate releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
    }
}
