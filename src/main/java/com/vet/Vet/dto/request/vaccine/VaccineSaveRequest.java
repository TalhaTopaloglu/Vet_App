package com.vet.Vet.dto.request.vaccine;

import com.vet.Vet.entities.Animal;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineSaveRequest {
    @NotNull(message = "Aşı ismi boş bırakılamaz.")
    private String name;
    @NotNull(message = "Aşı kodu boş bırakılamaz.")
    private String code;
    @NotNull(message = "Koruma başlangıç tarihi boş bırakılamaz.")
    private LocalDate protectionStartDate;
    @NotNull(message = "Koruma bitiş tarihi boş bırakılamaz.")
    private LocalDate protectionFinishDate;
    private int animalId;
    private int reportId;
}
