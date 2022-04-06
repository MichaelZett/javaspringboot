package de.zettsystems.netzfilm.movie.domain;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MovieRepositoryImpl implements MovieRepository {
    private static final Map<Long, Movie> REPO = new ConcurrentHashMap<>();

    @Override
    public List<Movie> findAll() {
        return List.copyOf(REPO.values());
    }

    @Override
    public Optional<Movie> findById(Long id) {
        final Movie movie = REPO.get(id);
        return Optional.ofNullable(movie);
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        for (Movie movie : REPO.values()) {
            if (movie.getTitle().equals(title)) {
                return Optional.of(movie);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean save(Movie movie) {
        return REPO.put(movie.getId(), movie) == null;
    }

}
