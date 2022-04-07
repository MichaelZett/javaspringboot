package de.zettsystems.netzfilm.movie.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoadCopyData {
    private final CopyRepository copyRepository;
    private final MovieRepository movieRepository;

    public void createCopies() {
        Movie movie = movieRepository.findByTitle("Return of the Jedi").orElseThrow();
        Movie movie2 = movieRepository.findByTitle("A new hope").orElseThrow();
        Movie movie3 = movieRepository.findByTitle("The Empire strikes back").orElseThrow();
        Copy copy = new Copy(CopyType.VHS, movie);
        copyRepository.save(copy);
        Copy copy2 = new Copy(CopyType.DVD, movie2);
        copyRepository.save(copy2);
        Copy copy3 = new Copy(CopyType.DVD, movie3);
        copyRepository.save(copy3);
    }

}
