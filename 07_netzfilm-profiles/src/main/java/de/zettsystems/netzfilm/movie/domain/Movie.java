package de.zettsystems.netzfilm.movie.domain;


import lombok.Getter;
import lombok.ToString;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@ToString
public class Movie {
    @Id
    @GeneratedValue
    private long id;
    @Version
    private long version;
    private UUID uuid;
    private String title;
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
}
