package com.vet.Vet.dao;

import com.vet.Vet.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepo extends JpaRepository<Report, Integer> {
    Optional<Report> findByAppointmentId(Integer appointment_id);
}
