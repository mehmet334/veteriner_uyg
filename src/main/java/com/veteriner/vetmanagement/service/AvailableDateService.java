package com.veteriner.vetmanagement.service;

import com.veteriner.vetmanagement.entity.AvailableDate;

import java.util.List;

public interface AvailableDateService {
    AvailableDate createAvailableDate(AvailableDate availableDate);
    AvailableDate updateAvailableDate(Long id, AvailableDate availableDate);
    void deleteAvailableDate(Long id);
    AvailableDate getAvailableDate(Long id);
    List<AvailableDate> getAllAvailableDates();
    List<AvailableDate> getAvailableDatesByDoctorId(Long doctorId);
}
