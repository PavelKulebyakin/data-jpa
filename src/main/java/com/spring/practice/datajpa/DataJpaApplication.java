package com.spring.practice.datajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import com.spring.practice.datajpa.entity.Customer;
import com.spring.practice.datajpa.repository.CustomerRepository;

import java.util.Optional;

@SpringBootApplication
public class DataJpaApplication {

    private static final Logger log = LoggerFactory.getLogger(DataJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DataJpaApplication.class, args);
    }

    @Bean
    @Profile(value = "test1")
    public ApplicationRunner dataLoader (CustomerRepository repository) {
        return (args) -> {
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));
        };
    }

    @Bean
    @Profile(value = "test2")
    public ApplicationRunner dataAccessor (CustomerRepository repository) {
        return (args) -> {

            // fetch all customers
            log.info("");
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Optional<Customer> customer = repository.findById(1L);
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> log.info(bauer.toString()));
            log.info("");
        };
    }

}
