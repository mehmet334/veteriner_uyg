package com.veteriner.vetmanagement.service;

import com.veteriner.vetmanagement.entity.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor createDoctor(Doctor doctor);
    Doctor updateDoctor(Long id, Doctor doctor);
    void deleteDoctor(Long id);
    Doctor getDoctor(Long id);
    List<Doctor> getAllDoctors();
}
