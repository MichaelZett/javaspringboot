package de.zettsystems.netzfilm.movie.application;

import de.zettsystems.netzfilm.movie.domain.Movie;
import de.zettsystems.netzfilm.movie.domain.MovieRepository;
import de.zettsystems.netzfilm.movie.values.MovieDbEntry;
import de.zettsystems.netzfilm.movie.values.MovieDbOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoadMovieData {
    private final MovieRepository movieRepository;
    private final MovieDbExternalService movieDbExternalService;

    public void createMovies() {
        final List<MovieDbEntry> movieDbEntries = movieDbExternalService.orderNewMovies(new MovieDbOrder(List.of("A new hope", "The Empire strikes back", "Return of the Jedi")));
        movieDbEntries.stream().map(LoadMovieData::toMovieEntity).forEach(movieRepository::save);
    }

    private static Movie toMovieEntity(MovieDbEntry movieDbEntry) {
        return new Movie(movieDbEntry);
    }

}
