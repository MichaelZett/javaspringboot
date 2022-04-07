package de.zettsystems.netzfilm.movie.adapter;

import de.zettsystems.netzfilm.movie.application.CopyService;
import de.zettsystems.netzfilm.movie.domain.Movie;
import de.zettsystems.netzfilm.movie.domain.MovieRepository;
import de.zettsystems.netzfilm.movie.values.CreateMovieData;
import de.zettsystems.netzfilm.movie.values.MovieData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Tag(name = "Movie Controller", description = "CRUD Controller for Movie")
@RestController
@RequestMapping("/api/movie")
@Slf4j
@RequiredArgsConstructor
class MovieController {
    private final MovieRepository repository;
    private final CopyService copyService;

    @Operation(summary = "Find all Movies", description = "List of all Movies in Netzfilm.")
    @GetMapping
    @Transactional(readOnly = true)
    public List<MovieData> findAll() {
        return repository.findAll().stream().map(this::mapMovie).collect(Collectors.toList());
    }

    @Operation(summary = "Find a specific Movie", description = "Find a specific Movie with the given uuid.")
    @GetMapping(value = "/{uuid}")
    @Transactional(readOnly = true)
    public MovieData findByUuid(@PathVariable("uuid") UUID uuid) {
        final Movie Movie = repository.findByUuid(uuid).orElseThrow();
        return mapMovie(Movie);
    }

    @Operation(summary = "Create a Movie", description = "Create a specific Movie with the given data.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, UUID> create(@Valid @RequestBody CreateMovieData resource) {
        return Map.of("uuid", repository.save(new Movie(resource.getTitle(), resource.getReleaseDate())).getUuid());
    }

    @Operation(summary = "Update a Movie", description = "Update the Movie with the given uuid and data.")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody MovieData resource) {
        final Movie movie = repository.findByUuidAndVersion(resource.getUuid(), resource.getVersion()).orElseThrow();
        movie.updateData(resource.getTitle(), resource.getReleaseDate());
        // we don't want a tx here, because of optimistic locking
        repository.save(movie);
        /* alternatively send and receive all of the entities' data (id as well), then one does not need the fetch
          but you would show the id to the outside world
         */
    }

    @Secured("ROLE_ADMIN")
    @Operation(summary = "Delete a Movie", description = "Delete the csutomer with the given uuid.")
    @DeleteMapping(value = "/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("uuid") UUID uuid) {
        repository.deleteByUuid(uuid);
    }

    private MovieData mapMovie(Movie Movie) {
        return new MovieData(Movie.getUuid(), Movie.getTitle(), Movie.getReleaseDate(), Movie.getVersion());
    }
}