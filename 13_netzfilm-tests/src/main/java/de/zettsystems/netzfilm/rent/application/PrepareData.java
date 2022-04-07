package de.zettsystems.netzfilm.rent.application;

import de.zettsystems.netzfilm.customer.domain.Customer;
import de.zettsystems.netzfilm.customer.domain.LoadCustomerData;
import de.zettsystems.netzfilm.movie.domain.Copy;
import de.zettsystems.netzfilm.movie.domain.CopyRepository;
import de.zettsystems.netzfilm.movie.domain.LoadCopyData;
import de.zettsystems.netzfilm.movie.domain.LoadMovieData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrepareData {
    private final LoadMovieData loadMovieData;
    private final LoadCopyData loadCopyData;
    private final LoadCustomerData loadCustomerData;
    private final RentService rentService;
    private final CopyRepository copyRepository;

    @PostConstruct
    @Transactional
    public void prepareData() {
        LOG.info("Preparing data...");
        loadMovieData.createMovies();
        loadCopyData.createCopies();
        final Customer customer = loadCustomerData.createCustomer();
        final Copy copy =
                copyRepository.findAllByLentFalse().stream().findFirst().get();
        rentService.rentAMovie(customer.getUuid(), copy.getUuid(), LocalDate.now(), 5L);
        LOG.info("...finished.");
    }

}
