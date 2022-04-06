package de.zettsystems.netzfilm.movie.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    void shouldUseOptimisticLockingHandling() throws InterruptedException {
        // given
        final Movie aNewHope = movieRepository.save(new Movie("A new hope", LocalDate.of(1977, 5, 25)));
        assertEquals(0, aNewHope.getVersion());

        // when
        final ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                final Optional<Movie> movie = movieRepository.findById(aNewHope.getId());
                movie.get().updateTitle("Title");
                movieRepository.saveAndFlush(movie.get());
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

    }
}