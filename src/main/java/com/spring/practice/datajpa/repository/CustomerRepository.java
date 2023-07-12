package com.spring.practice.datajpa.repository;

import com.spring.practice.datajpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository
        extends JpaRepository<Customer, Long> {
}
