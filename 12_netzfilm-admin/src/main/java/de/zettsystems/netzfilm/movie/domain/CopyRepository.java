package de.zettsystems.netzfilm.movie.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CopyRepository extends JpaRepository<Copy, Long> {
    Optional<Copy> findByUuid(UUID uuid);

    Optional<Copy> findByMovie(Movie movie);

    List<Copy> findAllByLentFalse();

    long countAllByMovieAndLentFalse(Movie movie);
}
