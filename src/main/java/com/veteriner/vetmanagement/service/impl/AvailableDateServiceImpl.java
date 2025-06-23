package com.veteriner.vetmanagement.service.impl;

import com.veteriner.vetmanagement.entity.AvailableDate;
import com.veteriner.vetmanagement.entity.Doctor;
import com.veteriner.vetmanagement.exception.NotFoundException;
import com.veteriner.vetmanagement.repository.AvailableDateRepository;
import com.veteriner.vetmanagement.repository.DoctorRepository;
import com.veteriner.vetmanagement.service.AvailableDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailableDateServiceImpl implements AvailableDateService {

    private final AvailableDateRepository availableDateRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public AvailableDate createAvailableDate(AvailableDate availableDate) {
        Long doctorId = availableDate.getDoctor().getId();
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException(doctorId + " id'li doktor bulunamadı."));
        availableDate.setDoctor(doctor);
        return availableDateRepository.save(availableDate);
    }

    @Override
    public AvailableDate updateAvailableDate(Long id, AvailableDate availableDate) {
        AvailableDate existing = getAvailableDate(id);
        existing.setAvailableDate(availableDate.getAvailableDate());
        return availableDateRepository.save(existing);
    }

    @Override
    public void deleteAvailableDate(Long id) {
        AvailableDate existing = getAvailableDate(id);
        availableDateRepository.delete(existing);
    }

    @Override
    public AvailableDate getAvailableDate(Long id) {
        return availableDateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id + " id'li müsait gün kaydı bulunamadı."));
    }

    @Override
    public List<AvailableDate> getAllAvailableDates() {
        return availableDateRepository.findAll();
    }

    @Override
    public List<AvailableDate> getAvailableDatesByDoctorId(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException(doctorId + " id'li doktor bulunamadı."));
        return availableDateRepository.findByDoctor(doctor);
    }
}
