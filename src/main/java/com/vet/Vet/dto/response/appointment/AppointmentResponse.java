package com.vet.Vet.dto.response.appointment;

import com.vet.Vet.entities.Animal;
import com.vet.Vet.entities.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    private int id;
    private LocalDateTime appointmentDate;
    private Animal animal;
    private Doctor doctor;
}
