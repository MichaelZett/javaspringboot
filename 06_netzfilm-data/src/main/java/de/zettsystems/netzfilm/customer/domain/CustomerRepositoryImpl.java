package de.zettsystems.netzfilm.customer.domain;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private static final Map<Long, Customer> REPO = new ConcurrentHashMap<>();

    @Override
    public List<Customer> findAll() {
        return REPO.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> findById(long id) {
        final Customer customer = REPO.get(id);
        return Optional.ofNullable(customer);
    }

    @Override
    public Optional<Customer> findByLastName(String lastName) {
        for (Customer movie : REPO.values()) {
            if (movie.getLastName().equals(lastName)) {
                return Optional.of(movie);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean save(Customer movie) {
        return REPO.put(movie.getId(), movie) == null;
    }
}
