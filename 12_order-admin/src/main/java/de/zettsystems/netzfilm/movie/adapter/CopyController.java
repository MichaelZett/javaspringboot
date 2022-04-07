package de.zettsystems.netzfilm.movie.adapter;

import de.zettsystems.netzfilm.movie.application.CopyService;
import de.zettsystems.netzfilm.movie.values.CreateCopyData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Tag(name = "Copy Controller", description = "CRUD Controller for Copy")
@RestController
@RequestMapping("/api/copy")
@Slf4j
@RequiredArgsConstructor
class CopyController {
    private final CopyService copyService;

    @Operation(summary = "Create copy", description = "Create the given number of copies for the given movie. ")
    @PostMapping()
    public Map<String, List<UUID>> createCopies(@Valid @RequestBody CreateCopyData data) {
        final List<UUID> uuids = copyService.createANumberOfCopies(data.getMovieUuid(), data.getNumber(), data.getCopyType());
        return Map.of("uuids", uuids);
    }

}