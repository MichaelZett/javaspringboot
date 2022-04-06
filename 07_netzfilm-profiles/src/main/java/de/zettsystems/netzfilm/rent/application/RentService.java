package de.zettsystems.netzfilm.rent.application;

import java.time.LocalDate;
import java.util.UUID;

public interface RentService {
    void rentAMovie(UUID customerUuid, UUID copyUuid, LocalDate start, long numberOfDays);
}
