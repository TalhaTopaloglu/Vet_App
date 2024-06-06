package com.vet.Vet.dto.request.availableDate;

import com.vet.Vet.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateSaveRequest {

    @NotNull
    private LocalDate availableDate;
    @NotNull
    private int doctorId;
}
