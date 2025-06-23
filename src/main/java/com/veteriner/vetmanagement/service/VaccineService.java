package com.veteriner.vetmanagement.service;

import com.veteriner.vetmanagement.entity.Vaccine;

import java.time.LocalDate;
import java.util.List;

public interface VaccineService {
    Vaccine createVaccine(Vaccine vaccine);
    Vaccine updateVaccine(Long id, Vaccine vaccine);
    void deleteVaccine(Long id);
    Vaccine getVaccine(Long id);
    List<Vaccine> getAllVaccines();
    List<Vaccine> getVaccinesByAnimalId(Long animalId);
    List<Vaccine> getVaccinesByProtectionFinishDateRange(LocalDate start, LocalDate end);
}
