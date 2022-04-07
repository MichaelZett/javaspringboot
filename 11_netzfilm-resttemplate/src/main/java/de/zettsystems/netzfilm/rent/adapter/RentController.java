package de.zettsystems.netzfilm.rent.adapter;

import de.zettsystems.netzfilm.movie.application.CopyService;
import de.zettsystems.netzfilm.movie.values.RentableCopy;
import de.zettsystems.netzfilm.rent.application.RentService;
import de.zettsystems.netzfilm.rent.values.CreateRentData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Tag(name = "Rent Controller", description = "Controller for renting movies")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class RentController {
    private final CopyService copyService;
    private final RentService rentService;

    @Operation(summary = "Show all rentable copies", description = "Show all rentable copies, meaning copies not currently rented")
    @GetMapping("/rentables")
    public List<RentableCopy> getRentableCopies() {
        final List<RentableCopy> allRentableCopies = copyService.findAllRentableCopies();
        return allRentableCopies;
    }

    @Operation(summary = "Create a rent", description = "Create a rent for the given customer and copy. Starting on the given date for the given days.")
    @PostMapping("/rent")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, UUID> rent(@Valid @RequestBody CreateRentData data) {
        return Map.of("uuid", rentService.rentAMovie(data.getCustomerUuid(), data.getCopyUuid(),  data.getStartDate(), data.getDays()));
    }

}