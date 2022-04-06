package de.zettsystems.netzfilm.movie.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.util.UUID;

@Entity
@Getter
@ToString
public class Copy {
    @Id
    @GeneratedValue
    private long id;
    @Version
    private long version;
    private UUID uuid;
    @Enumerated(EnumType.STRING)
    private CopyType type;
    @ManyToOne
    private Movie movie;
    private boolean lent;

    //needed for jpa
    protected Copy() {
        super();
        this.uuid = UUID.randomUUID();
    }

    public Copy(CopyType type, Movie movie) {
        this();
        this.lent = false;
        this.type = type;
        this.movie = movie;
    }

    public void lend() {
        this.lent = true;
    }

}
