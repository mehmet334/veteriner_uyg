package com.veteriner.vetmanagement.repository;

import com.veteriner.vetmanagement.entity.Animal;
import com.veteriner.vetmanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByNameContainingIgnoreCase(String name);
    List<Animal> findByCustomer(Customer customer);
}
