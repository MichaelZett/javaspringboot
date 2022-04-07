package de.zettsystems.order.movie.adapter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api/movie")
class MovieDbController {
    @PostMapping
    public ResponseEntity<Map<String,List<MovieDbEntry>>> order(@RequestBody MovieDbOrder order) {
        LOG.info("Got an order {}", order);
        final List<MovieDbEntry> delivery = order.getTitles().stream().map(MovieDbController::toMovieDbEntry).collect(Collectors.toList());
        LOG.info("Prepared a delivery {}", delivery);
        final Map<String, List<MovieDbEntry>> body = Map.of("delivery", delivery);
        return ResponseEntity.of(Optional.of(body));
    }

    private static MovieDbEntry toMovieDbEntry(String title) {
        LocalDate releaseDate = between();
        return new MovieDbEntry(title, releaseDate);
    }

    public static LocalDate between() {
        long startEpochDay = LocalDate.of(1950,1,1).toEpochDay();
        long endEpochDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }
}