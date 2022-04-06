package de.zettsystems.netzfilm.movie.application;

import de.zettsystems.netzfilm.movie.domain.Movie;
import de.zettsystems.netzfilm.movie.values.RentableCopy;

import java.util.List;
import java.util.UUID;

public interface CopyService {
    List<Movie> findAllMoviesWithoutFreeCopies();

    List<RentableCopy> findAllRentableCopies();

    String getTitleAndFormat(UUID uuid);
}
