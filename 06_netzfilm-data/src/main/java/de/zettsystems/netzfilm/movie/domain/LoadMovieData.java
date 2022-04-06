package de.zettsystems.netzfilm.movie.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoadMovieData {
    private final MovieRepository movieRepository;

    @PostConstruct
    public void createMovies() {
        movieRepository.save(new Movie("A new hope", LocalDate.of(1977, 5, 25)));
        movieRepository.save(new Movie("The Empire Strikes Back", LocalDate.of(1980, 5, 17)));
        Movie returnOfTheJedi = new Movie("Return of the Jedi", LocalDate.of(1983, 5, 25));
        returnOfTheJedi = movieRepository.save(returnOfTheJedi);
        final Optional<Movie> byId = movieRepository.findById(returnOfTheJedi.getId());
        LOG.info("Found one by id: {}", byId);
        LOG.info("Found one by title: {}", movieRepository.findByTitle("A new hope"));
        LOG.info("Found all: {}", movieRepository.findAll());
    }

}
