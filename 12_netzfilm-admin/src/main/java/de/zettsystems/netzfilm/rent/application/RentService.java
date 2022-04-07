package de.zettsystems.netzfilm.rent.application;

import de.zettsystems.netzfilm.rent.domain.Rent;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface RentService {
    UUID rentAMovie(UUID customerUuid, UUID copyUuid, LocalDate start, long numberOfDays);

    List<Rent> findAllRentsForCustomer(UUID customerUuid);
}
