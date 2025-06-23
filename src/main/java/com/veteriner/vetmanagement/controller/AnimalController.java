package com.veteriner.vetmanagement.controller;

import com.veteriner.vetmanagement.entity.Animal;
import com.veteriner.vetmanagement.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal animal) {
        return ResponseEntity.ok(animalService.createAnimal(animal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        return ResponseEntity.ok(animalService.updateAnimal(id, animal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimal(@PathVariable Long id) {
        return ResponseEntity.ok(animalService.getAnimal(id));
    }

    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals() {
        return ResponseEntity.ok(animalService.getAllAnimals());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Animal>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(animalService.searchByName(name));
    }

    @GetMapping("/by-customer/{customerId}")
    public ResponseEntity<List<Animal>> getAnimalsByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(animalService.getAnimalsByCustomerId(customerId));
    }
}
