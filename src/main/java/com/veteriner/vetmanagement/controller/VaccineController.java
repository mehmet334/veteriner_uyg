package com.veteriner.vetmanagement.controller;

import com.veteriner.vetmanagement.entity.Vaccine;
import com.veteriner.vetmanagement.service.VaccineService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
@RequiredArgsConstructor
public class VaccineController {

    private final VaccineService vaccineService;

    @PostMapping
    public ResponseEntity<Vaccine> createVaccine(@RequestBody Vaccine vaccine) {
        return ResponseEntity.ok(vaccineService.createVaccine(vaccine));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaccine> updateVaccine(@PathVariable Long id, @RequestBody Vaccine vaccine) {
        return ResponseEntity.ok(vaccineService.updateVaccine(id, vaccine));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaccine(@PathVariable Long id) {
        vaccineService.deleteVaccine(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaccine> getVaccine(@PathVariable Long id) {
        return ResponseEntity.ok(vaccineService.getVaccine(id));
    }

    @GetMapping
    public ResponseEntity<List<Vaccine>> getAllVaccines() {
        return ResponseEntity.ok(vaccineService.getAllVaccines());
    }

    @GetMapping("/by-animal/{animalId}")
    public ResponseEntity<List<Vaccine>> getVaccinesByAnimalId(@PathVariable Long animalId) {
        return ResponseEntity.ok(vaccineService.getVaccinesByAnimalId(animalId));
    }

    @GetMapping("/by-protection-finish-date")
    public ResponseEntity<List<Vaccine>> getVaccinesByProtectionFinishDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(vaccineService.getVaccinesByProtectionFinishDateRange(start, end));
    }
}
