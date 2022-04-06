package de.zettsystems.netzfilm.customer.domain;

import de.zettsystems.netzfilm.movie.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByLastName(String lastName);

    Optional<Customer> findByUuid(UUID customerUuid);
}
