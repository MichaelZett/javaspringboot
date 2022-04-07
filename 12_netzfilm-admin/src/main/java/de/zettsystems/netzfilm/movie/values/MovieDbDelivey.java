package de.zettsystems.netzfilm.movie.values;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDbDelivey {
    private List<MovieDbEntry> delivery;
}
