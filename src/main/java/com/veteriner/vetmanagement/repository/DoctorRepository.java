package com.veteriner.vetmanagement.repository;

import com.veteriner.vetmanagement.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
