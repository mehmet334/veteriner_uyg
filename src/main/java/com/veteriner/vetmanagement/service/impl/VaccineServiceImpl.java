package com.veteriner.vetmanagement.service.impl;

import com.veteriner.vetmanagement.entity.Animal;
import com.veteriner.vetmanagement.entity.Vaccine;
import com.veteriner.vetmanagement.exception.ConflictException;
import com.veteriner.vetmanagement.exception.NotFoundException;
import com.veteriner.vetmanagement.repository.AnimalRepository;
import com.veteriner.vetmanagement.repository.VaccineRepository;
import com.veteriner.vetmanagement.service.VaccineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccineServiceImpl implements VaccineService {

    private final VaccineRepository vaccineRepository;
    private final AnimalRepository animalRepository;

    @Override
    public Vaccine createVaccine(Vaccine vaccine) {
        Long animalId = vaccine.getAnimal().getId();
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new NotFoundException(animalId + " id'li hayvan bulunamadı."));

        boolean exists = vaccineRepository.findByAnimalAndNameAndCode(animal, vaccine.getName(), vaccine.getCode())
                .stream()
                .anyMatch(v -> v.getProtectionFinishDate().isAfter(LocalDate.now()));
        if (exists) {
            throw new ConflictException("Koruyuculuğu bitmemiş aynı tipte bir aşı zaten mevcut.");
        }

        vaccine.setAnimal(animal);
        return vaccineRepository.save(vaccine);
    }

    @Override
    public Vaccine updateVaccine(Long id, Vaccine vaccine) {
        Vaccine existing = getVaccine(id);
        existing.setName(vaccine.getName());
        existing.setCode(vaccine.getCode());
        existing.setProtectionStartDate(vaccine.getProtectionStartDate());
        existing.setProtectionFinishDate(vaccine.getProtectionFinishDate());
        return vaccineRepository.save(existing);
    }

    @Override
    public void deleteVaccine(Long id) {
        Vaccine existing = getVaccine(id);
        vaccineRepository.delete(existing);
    }

    @Override
    public Vaccine getVaccine(Long id) {
        return vaccineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id + " id'li aşı kaydı bulunamadı."));
    }

    @Override
    public List<Vaccine> getAllVaccines() {
        return vaccineRepository.findAll();
    }

    @Override
    public List<Vaccine> getVaccinesByAnimalId(Long animalId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new NotFoundException(animalId + " id'li hayvan bulunamadı."));
        return vaccineRepository.findByAnimal(animal);
    }

    @Override
    public List<Vaccine> getVaccinesByProtectionFinishDateRange(LocalDate start, LocalDate end) {
        return vaccineRepository.findByProtectionFinishDateBetween(start, end);
    }
}
