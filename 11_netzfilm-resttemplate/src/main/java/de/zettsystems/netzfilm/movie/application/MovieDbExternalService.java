package de.zettsystems.netzfilm.movie.application;

import de.zettsystems.netzfilm.movie.values.MovieDbEntry;
import de.zettsystems.netzfilm.movie.values.MovieDbOrder;

import java.util.List;

public interface MovieDbExternalService {
    List<MovieDbEntry> orderNewMovies(MovieDbOrder order);

    List<MovieDbEntry> getMoviesCatalog();
}
