package com.veteriner.vetmanagement.service.impl;

import com.veteriner.vetmanagement.entity.Animal;
import com.veteriner.vetmanagement.entity.Customer;
import com.veteriner.vetmanagement.exception.NotFoundException;
import com.veteriner.vetmanagement.repository.AnimalRepository;
import com.veteriner.vetmanagement.repository.CustomerRepository;
import com.veteriner.vetmanagement.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Animal createAnimal(Animal animal) {
        Long customerId = animal.getCustomer().getId();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException(customerId + " id'li müşteri bulunamadı."));
        animal.setCustomer(customer);
        return animalRepository.save(animal);
    }

    @Override
    public Animal updateAnimal(Long id, Animal animal) {
        Animal existing = getAnimal(id);
        existing.setName(animal.getName());
        existing.setSpecies(animal.getSpecies());
        existing.setBreed(animal.getBreed());
        existing.setGender(animal.getGender());
        existing.setColour(animal.getColour());
        existing.setDateOfBirth(animal.getDateOfBirth());
        return animalRepository.save(existing);
    }

    @Override
    public void deleteAnimal(Long id) {
        Animal existing = getAnimal(id);
        animalRepository.delete(existing);
    }

    @Override
    public Animal getAnimal(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id + " id'li hayvan bulunamadı."));
    }

    @Override
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    @Override
    public List<Animal> searchByName(String name) {
        return animalRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Animal> getAnimalsByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException(customerId + " id'li müşteri bulunamadı."));
        return animalRepository.findByCustomer(customer);
    }
}
