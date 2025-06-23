package com.veteriner.vetmanagement.controller;

import com.veteriner.vetmanagement.entity.AvailableDate;
import com.veteriner.vetmanagement.service.AvailableDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/available-dates")
@RequiredArgsConstructor
public class AvailableDateController {

    private final AvailableDateService availableDateService;

    @PostMapping
    public ResponseEntity<AvailableDate> createAvailableDate(@RequestBody AvailableDate availableDate) {
        return ResponseEntity.ok(availableDateService.createAvailableDate(availableDate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvailableDate> updateAvailableDate(@PathVariable Long id, @RequestBody AvailableDate availableDate) {
        return ResponseEntity.ok(availableDateService.updateAvailableDate(id, availableDate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailableDate(@PathVariable Long id) {
        availableDateService.deleteAvailableDate(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvailableDate> getAvailableDate(@PathVariable Long id) {
        return ResponseEntity.ok(availableDateService.getAvailableDate(id));
    }

    @GetMapping
    public ResponseEntity<List<AvailableDate>> getAllAvailableDates() {
        return ResponseEntity.ok(availableDateService.getAllAvailableDates());
    }

    @GetMapping("/by-doctor/{doctorId}")
    public ResponseEntity<List<AvailableDate>> getAvailableDatesByDoctorId(@PathVariable Long doctorId) {
        return ResponseEntity.ok(availableDateService.getAvailableDatesByDoctorId(doctorId));
    }
}
