package de.zettsystems.netzfilm.customer.adapter;

import de.zettsystems.netzfilm.customer.domain.Customer;
import de.zettsystems.netzfilm.customer.domain.CustomerRepository;
import de.zettsystems.netzfilm.customer.values.CreateCustomerData;
import de.zettsystems.netzfilm.customer.values.CustomerData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Tag(name = "Customer Controller", description = "CRUD Controller for Customer")
@RestController
@RequestMapping("/api/customer")
@Slf4j
@RequiredArgsConstructor
class CustomerController {
    private final CustomerRepository repository;

    @Operation(summary = "Find all Customers", description = "List of all Customers in Netzfilm.")
    @GetMapping
    @Transactional(readOnly = true)
    public List<CustomerData> findAll() {
        return repository.findAll().stream().map(this::mapCustomer).collect(Collectors.toList());
    }

    @Operation(summary = "Find a specific Customer", description = "Find a specific Customer with the given uuid.")
    @GetMapping(value = "/{uuid}")
    @Transactional(readOnly = true)
    public CustomerData findByUuid(@PathVariable("uuid") UUID uuid) {
        final Customer customer = repository.findByUuid(uuid).orElseThrow();
        return mapCustomer(customer);
    }

    @Operation(summary = "Creae a Customer", description = "Create a specific Customer with the given data.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, UUID> create(@Valid @RequestBody CreateCustomerData resource) {
        return Map.of("uuid", repository.save(new Customer(resource.getName(), resource.getLastName(), resource.getBirthdate())).getUuid());
    }

    @Operation(summary = "Update a Customer", description = "Update the Customer with the given uuid and data.")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody CustomerData resource) {
        final Customer customer = repository.findByUuidAndVersion(resource.getUuid(), resource.getVersion()).orElseThrow();
        customer.updateData(resource.getName(), resource.getLastName(), resource.getBirthdate());
        // we don't want a tx here, because of optimistic locking
        repository.save(customer);
        /* alternatively send and receive all of the entities' data (id as well), then one does not need the fetch
          but you would show the id to the outside world
         */
    }

    @Operation(summary = "Delete a Customer", description = "Delete the csutomer with the given uuid.")
    @DeleteMapping(value = "/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("uuid") UUID uuid) {
        repository.deleteByUuid(uuid);
    }

    private CustomerData mapCustomer(Customer customer) {
        return new CustomerData(customer.getUuid(), customer.getName(), customer.getLastName(), customer.getBirthdate(), customer.getVersion());
    }
}