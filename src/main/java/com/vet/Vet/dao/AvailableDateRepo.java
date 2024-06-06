package com.vet.Vet.dao;

import com.vet.Vet.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate,Integer> {
    Optional<AvailableDate> findByAvailableDateAndDoctorId(LocalDate availableDate, int doctorId);
}
