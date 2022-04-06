package de.zettsystems.netzfilm.movie.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class LoadMovieData {
    private final MovieRepository movieRepository;

    public LoadMovieData(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void createMovies() {
        movieRepository.save(new Movie("A new hope", LocalDate.of(1977, 5, 25)));
        movieRepository.save(new Movie("The Empire strikes back", LocalDate.of(1980, 5, 17)));
        movieRepository.save(new Movie("Return of the Jedi", LocalDate.of(1983, 5, 25)));
    }

}
