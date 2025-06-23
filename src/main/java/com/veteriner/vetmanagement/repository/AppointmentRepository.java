package com.veteriner.vetmanagement.repository;

import com.veteriner.vetmanagement.entity.Appointment;
import com.veteriner.vetmanagement.entity.Animal;
import com.veteriner.vetmanagement.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByDoctorAndAppointmentDate(Doctor doctor, LocalDateTime appointmentDate);
    List<Appointment> findByDoctorAndAppointmentDateBetween(Doctor doctor, LocalDateTime start, LocalDateTime end);
    List<Appointment> findByAnimalAndAppointmentDateBetween(Animal animal, LocalDateTime start, LocalDateTime end);
}
