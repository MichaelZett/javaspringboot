package de.zettsystems.netzfilm.movie.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieRepository {
    Optional<Movie> findById(Long id);

    Optional<Movie> findByTitle(String title);

    boolean save(Movie movie);

    List<Movie> findAll();
}
