package com.veteriner.vetmanagement.service.impl;

import com.veteriner.vetmanagement.entity.Doctor;
import com.veteriner.vetmanagement.exception.NotFoundException;
import com.veteriner.vetmanagement.exception.ConflictException;
import com.veteriner.vetmanagement.repository.DoctorRepository;
import com.veteriner.vetmanagement.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public Doctor createDoctor(Doctor doctor) {
        boolean exists = doctorRepository.findAll().stream()
                .anyMatch(d -> d.getMail().equalsIgnoreCase(doctor.getMail()));
        if (exists) {
            throw new ConflictException("Kayıt sistemde mevcut.");
        }
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctor) {
        Doctor existing = getDoctor(id);
        existing.setName(doctor.getName());
        existing.setPhone(doctor.getPhone());
        existing.setMail(doctor.getMail());
        existing.setAddress(doctor.getAddress());
        existing.setCity(doctor.getCity());
        return doctorRepository.save(existing);
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor existing = getDoctor(id);
        doctorRepository.delete(existing);
    }

    @Override
    public Doctor getDoctor(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id + " id'li doktor bulunamadı."));
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}
