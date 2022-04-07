package de.zettsystems.netzfilm.customer.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void findByUuidAndVersion() {
        // given
        Customer bernd = new Customer("Bernd", "das Brot", LocalDate.now());
        entityManager.persist(bernd);
        entityManager.flush();

        // when
        Customer found = customerRepository.findByUuidAndVersion(bernd.getUuid(), bernd.getVersion()).orElseThrow();

        // then
        assertThat(found.getName()).isEqualTo(bernd.getName());
        assertThat(found.getLastName()).isEqualTo(bernd.getLastName());
        assertThat(found.getBirthdate()).isEqualTo(bernd.getBirthdate());
    }
}