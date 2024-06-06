package com.vet.Vet.dto.request.appointment;

import com.vet.Vet.entities.Animal;
import com.vet.Vet.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentUpdateRequest {
    @Positive(message = "ID değeri pozitif bir değer olmalı.")
    @NotNull
    private int id;
    @NotNull
    private LocalDateTime appointmentDate;
    @NotNull
    private int animalId;
    @NotNull
    private int doctorId;
}
