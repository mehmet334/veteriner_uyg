package com.veteriner.vetmanagement.service;

import com.veteriner.vetmanagement.entity.Animal;

import java.util.List;

public interface AnimalService {
    Animal createAnimal(Animal animal);
    Animal updateAnimal(Long id, Animal animal);
    void deleteAnimal(Long id);
    Animal getAnimal(Long id);
    List<Animal> getAllAnimals();
    List<Animal> searchByName(String name);
    List<Animal> getAnimalsByCustomerId(Long customerId);
}
