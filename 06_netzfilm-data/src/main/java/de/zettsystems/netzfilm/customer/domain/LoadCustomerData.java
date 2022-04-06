package de.zettsystems.netzfilm.customer.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoadCustomerData {
    private static final AtomicLong SEQUENCE = new AtomicLong();
    private final CustomerRepository customerRepository;

    @PostConstruct
    public void createCustomers() {
        customerRepository.save(new Customer(SEQUENCE.getAndIncrement(), "Petra", "Meyer", LocalDate.of(1963, 7, 11)));
        customerRepository.save(new Customer(SEQUENCE.getAndIncrement(), "Frank", "Schuhmacher", LocalDate.of(1976, 4, 3)));
        final Customer customer = new Customer(SEQUENCE.getAndIncrement(), "Wiebke", "Müller", LocalDate.of(1984, 11, 23));
        customerRepository.save(customer);
        final Optional<Customer> byId = customerRepository.findById(customer.getId());
        LOG.info("Found one by id: {}", byId);
        LOG.info("Found one by lastname: {}", customerRepository.findByLastName("Meyer"));
        LOG.info("Found all: {}", customerRepository.findAll());
    }

}
