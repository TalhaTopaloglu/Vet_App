package com.vet.Vet.dto.request.appointment;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSaveRequest {
    @NotNull
    private LocalDateTime appointmentDate;
    @NotNull
    private int animalId;
    @NotNull
    private int doctorId;
}
