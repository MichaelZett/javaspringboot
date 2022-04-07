package de.zettsystems.netzfilm.movie.application;

import de.zettsystems.netzfilm.movie.domain.*;
import de.zettsystems.netzfilm.movie.values.RentableCopy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CopyServiceImpl implements CopyService {
    private final CopyRepository copyRepository;
    private final MovieRepository movieRepository;

    public CopyServiceImpl(CopyRepository copyRepository, MovieRepository movieRepository) {
        this.copyRepository = copyRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> findAllMoviesWithoutFreeCopies() {
        return movieRepository.findAll().stream()
                .filter(movie -> copyRepository.countAllByMovieAndLentFalse(movie) == 0L)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<RentableCopy> findAllRentableCopies() {
        final List<Copy> allByLentFalse = copyRepository.findAllByLentFalse();
        return allByLentFalse.stream().map(c -> new RentableCopy(c.getUuid(), getTitleAndFormat(c))).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String getTitleAndFormat(UUID uuid) {
        return getTitleAndFormat(copyRepository.findByUuid(uuid).orElseThrow());
    }

    @Transactional
    @Override
    public List<UUID> createANumberOfCopies(UUID movieUuid, Long number, CopyType copyType) {
        final Movie movie = movieRepository.findByUuid(movieUuid).orElseThrow(() -> new RuntimeException("No such movie"));
        List<UUID> result = new LinkedList<>();
        for(int i = 0; i < number; i++) {
            Copy copy = new Copy(copyType, movie);
            final Copy save = copyRepository.save(copy);
            result.add(save.getUuid());
        }
        return result;
    }

    private String getTitleAndFormat(Copy c) {
        return c.getMovie().getTitle() + " - " + c.getType().toString();
    }

}
