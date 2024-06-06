package com.vet.Vet.dao;

import com.vet.Vet.entities.Animal;
import com.vet.Vet.entities.Appointment;
import com.vet.Vet.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
    Optional<Appointment> findByAppointmentDateAndDoctorId(LocalDateTime localDateTime, int doctorId);
    @Query("SELECT a FROM Appointment a WHERE a.appointmentDate BETWEEN ?1 AND ?2 AND a.doctor = ?3")
    List<Appointment> findAppointmentsByDateAndDoctor(LocalDateTime startDate, LocalDateTime finishDate, Doctor doctor);

    @Query("SELECT a FROM Appointment a WHERE a.appointmentDate BETWEEN ?1 AND ?2 AND a.animal = ?3")
    List<Appointment> findAppointmentsByDateAndAnimal(LocalDateTime startDate, LocalDateTime finishDate, Animal animal);

}
