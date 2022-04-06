package de.zettsystems.netzfilm.movie.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByTitle(String title);

    //    @Modifying needed for update,delete insert
    // ,nativeQuery=true) --> Write SQL and not JPQL

    // Next two methods are doing the same
    @Query(value = "SELECT m FROM Movie m WHERE m.title = :title AND m.releaseDate < :date ")
    Optional<Movie> findSomethingSpecial(@Param("title") String title, @Param("date") LocalDate date);

    Optional<Movie> findByTitleAndReleaseDateBefore(String title, LocalDate date);
}
