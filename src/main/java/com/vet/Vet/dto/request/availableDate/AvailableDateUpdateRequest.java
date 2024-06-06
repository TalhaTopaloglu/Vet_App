package com.vet.Vet.dto.request.availableDate;

import com.vet.Vet.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateUpdateRequest {
    @Positive(message = "ID değeri pozitif bir değer olmalı.")
    @NotNull
    private int id;
    @NotNull
    private LocalDate availableDate;
    @NotNull
    private int doctorId;
}
