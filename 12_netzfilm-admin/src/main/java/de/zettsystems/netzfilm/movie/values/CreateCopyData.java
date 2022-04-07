package de.zettsystems.netzfilm.movie.values;

import de.zettsystems.netzfilm.movie.domain.CopyType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CreateCopyData {
    @NotNull
    private UUID movieUuid;
    @NotNull
    @Min(1)
    private Long number;
    @NotNull
    private CopyType copyType;

    public CreateCopyData() {
        super();
    }
}
