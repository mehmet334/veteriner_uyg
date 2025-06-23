package com.veteriner.vetmanagement.service.impl;

import com.veteriner.vetmanagement.entity.Appointment;
import com.veteriner.vetmanagement.entity.AvailableDate;
import com.veteriner.vetmanagement.entity.Doctor;
import com.veteriner.vetmanagement.entity.Animal;
import com.veteriner.vetmanagement.exception.ConflictException;
import com.veteriner.vetmanagement.exception.NotFoundException;
import com.veteriner.vetmanagement.repository.AppointmentRepository;
import com.veteriner.vetmanagement.repository.AvailableDateRepository;
import com.veteriner.vetmanagement.repository.DoctorRepository;
import com.veteriner.vetmanagement.repository.AnimalRepository;
import com.veteriner.vetmanagement.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final AnimalRepository animalRepository;
    private final AvailableDateRepository availableDateRepository;

    @Override
    public Appointment createAppointment(Appointment appointment) {
        Long doctorId = appointment.getDoctor().getId();
        Long animalId = appointment.getAnimal().getId();

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException(doctorId + " id'li doktor bulunamadı."));
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new NotFoundException(animalId + " id'li hayvan bulunamadı."));

        LocalDate appointmentDay = appointment.getAppointmentDate().toLocalDate();
        LocalDateTime appointmentDateTime = appointment.getAppointmentDate();

        // Müsait gün kontrolü
        boolean isAvailableDay = availableDateRepository.findByDoctor(doctor).stream()
                .anyMatch(date -> date.getAvailableDate().isEqual(appointmentDay));

        if (!isAvailableDay) {
            throw new ConflictException("Doktor bu tarihte çalışmamaktadır!");
        }

        // Saat çakışma kontrolü
        boolean hasConflict = appointmentRepository.findByDoctorAndAppointmentDate(doctor, appointmentDateTime).isPresent();

        if (hasConflict) {
            throw new ConflictException("Girilen saatte başka bir randevu mevcuttur.");
        }

        appointment.setDoctor(doctor);
        appointment.setAnimal(animal);

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment appointment) {
        Appointment existing = getAppointment(id);
        existing.setAppointmentDate(appointment.getAppointmentDate());
        return appointmentRepository.save(existing);
    }

    @Override
    public void deleteAppointment(Long id) {
        Appointment existing = getAppointment(id);
        appointmentRepository.delete(existing);
    }

    @Override
    public Appointment getAppointment(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id + " id'li randevu bulunamadı."));
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getAppointmentsByDoctorAndDateRange(Long doctorId, LocalDateTime start, LocalDateTime end) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException(doctorId + " id'li doktor bulunamadı."));
        return appointmentRepository.findByDoctorAndAppointmentDateBetween(doctor, start, end);
    }

    @Override
    public List<Appointment> getAppointmentsByAnimalAndDateRange(Long animalId, LocalDateTime start, LocalDateTime end) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new NotFoundException(animalId + " id'li hayvan bulunamadı."));
        return appointmentRepository.findByAnimalAndAppointmentDateBetween(animal, start, end);
    }
}
