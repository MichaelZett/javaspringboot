package de.zettsystems.netzfilm.movie.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByTitle(String title);

    void deleteByUuid(UUID uuid);

    Optional<Movie> findByUuidAndVersion(UUID uuid, Long version);

    Optional<Movie> findByUuid(UUID uuid);
}
