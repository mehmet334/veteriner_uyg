package com.veteriner.vetmanagement.repository;

import com.veteriner.vetmanagement.entity.AvailableDate;
import com.veteriner.vetmanagement.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {
    List<AvailableDate> findByDoctor(Doctor doctor);
}
