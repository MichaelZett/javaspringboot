package de.zettsystems.netzfilm.movie.adapter;

import de.zettsystems.netzfilm.movie.application.MovieDbExternalService;
import de.zettsystems.netzfilm.movie.values.MovieDbDelivey;
import de.zettsystems.netzfilm.movie.values.MovieDbEntry;
import de.zettsystems.netzfilm.movie.values.MovieDbOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieDbExternalServiceImpl implements MovieDbExternalService {
    private final RestTemplate movieDbRestTemplate;
    @Override
    public List<MovieDbEntry> orderNewMovies(MovieDbOrder order) {
        MovieDbDelivey delivery = movieDbRestTemplate.postForObject("/movie/", order, MovieDbDelivey.class);
        return delivery.getDelivery();
    }

    @PostConstruct
    public void test() {
        orderNewMovies(new MovieDbOrder(List.of("SomeTitle"))).forEach(System.out::println);
    }
}
