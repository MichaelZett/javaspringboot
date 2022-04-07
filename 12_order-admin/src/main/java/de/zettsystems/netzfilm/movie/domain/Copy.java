package de.zettsystems.netzfilm.movie.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@ToString
public class Copy {
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
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(updatable = false, nullable = false)
    private CopyType type;
    @ManyToOne
    @NotNull
    @Valid
    @JoinColumn(updatable = false, nullable = false)
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
