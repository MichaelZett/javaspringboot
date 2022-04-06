package de.zettsystems.netzfilm.movie.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
public class LoadMovieData {
    private static final AtomicLong SEQUENCE = new AtomicLong();
    private final MovieRepository movieRepository;

    public LoadMovieData(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostConstruct
    public void createMovies() {
        movieRepository.save(new Movie(SEQUENCE.getAndIncrement(), "A new hope", LocalDate.of(1977, 5, 25)));
        movieRepository.save(new Movie(SEQUENCE.getAndIncrement(), "The Empire Strikes Back", LocalDate.of(1980, 5, 17)));
        final Movie returnOfTheJedi = new Movie(SEQUENCE.getAndIncrement(), "Return of the Jedi", LocalDate.of(1983, 5, 25));
        movieRepository.save(returnOfTheJedi);

        final Optional<Movie> byId = movieRepository.findById(returnOfTheJedi.getId());
        LOG.info("Found one by id: {}", byId);
        LOG.info("Found one by title: {}", movieRepository.findByTitle("A new hope"));
        LOG.info("Found all: {}", movieRepository.findAll());
    }

}
