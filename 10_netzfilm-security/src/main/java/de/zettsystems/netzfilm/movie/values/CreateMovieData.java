package de.zettsystems.netzfilm.movie.values;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreateMovieData {
    @NotBlank
    private String title;
    @NotNull
    private LocalDate releaseDate;

    public CreateMovieData() {
        super();
    }
}
