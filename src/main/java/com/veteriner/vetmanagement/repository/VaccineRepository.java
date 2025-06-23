package com.veteriner.vetmanagement.repository;

import com.veteriner.vetmanagement.entity.Vaccine;
import com.veteriner.vetmanagement.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    List<Vaccine> findByAnimal(Animal animal);
    List<Vaccine> findByAnimalAndNameAndCode(Animal animal, String name, String code);
    List<Vaccine> findByProtectionFinishDateBetween(LocalDate start, LocalDate end);
}
