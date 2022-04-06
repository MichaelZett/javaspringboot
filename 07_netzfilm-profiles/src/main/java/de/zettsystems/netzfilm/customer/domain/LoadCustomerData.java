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
    private final CustomerRepository customerRepository;

    public Customer createCustomer() {
        customerRepository.save(new Customer("Petra", "Meyer", LocalDate.of(1963, 7, 11)));
        customerRepository.save(new Customer("Frank", "Schuhmacher", LocalDate.of(1976, 4, 3)));
        Customer customer = new Customer("Wiebke", "Müller", LocalDate.of(1984, 11, 23));
        customer = customerRepository.save(customer);
        return customer;
    }

}
