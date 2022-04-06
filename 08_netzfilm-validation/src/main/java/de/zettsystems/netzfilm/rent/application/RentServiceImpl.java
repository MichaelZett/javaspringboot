package de.zettsystems.netzfilm.rent.application;

import de.zettsystems.netzfilm.customer.domain.Customer;
import de.zettsystems.netzfilm.customer.domain.CustomerRepository;
import de.zettsystems.netzfilm.movie.domain.Copy;
import de.zettsystems.netzfilm.movie.domain.CopyRepository;
import de.zettsystems.netzfilm.movie.domain.CopyType;
import de.zettsystems.netzfilm.rent.domain.Rent;
import de.zettsystems.netzfilm.rent.domain.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {
    private static final BigDecimal TWO_EURO = new BigDecimal("2.0");
    private final CustomerRepository customerRepository;
    private final CopyRepository copyRepository;
    private final RentRepository rentRepository;

    @Override
    @Transactional
    public void rentAMovie(UUID customerUuid, UUID copyUuid, LocalDate start, long numberOfDays) {
        Customer customer = customerRepository.findByUuid(customerUuid).orElseThrow(() -> new RuntimeException("Customer not found"));
        Copy copy = copyRepository.findByUuid(copyUuid).orElseThrow(() -> new RuntimeException("Copy not found"));

        if (copy.isLent()) {
            throw new RuntimeException("Copy is already lent.");
        }

        BigDecimal amount = calculateAmount(copy.getType(), numberOfDays);

        Rent newRent = new Rent(copy, customer, amount, start, start.plusDays(numberOfDays));
        rentRepository.save(newRent);
        // the transaction is not closed yet, changes at JPA-ManagedBeans will
        // be persisted without further "save"-call
        copy.lend();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Rent> findAllRentsForCustomer(UUID customerUuid) {
        Customer customer = customerRepository.findByUuid(customerUuid).orElseThrow(() -> new RuntimeException("Customer not found"));
        return rentRepository.findAllByCustomer(customer);
    }

    private static BigDecimal calculateAmount(CopyType type, long numberOfDays) {
        if (CopyType.VHS == type) {
            return BigDecimal.ONE.multiply(BigDecimal.valueOf(numberOfDays));
        } else {
            return TWO_EURO.multiply(BigDecimal.valueOf(numberOfDays));
        }
    }
}
