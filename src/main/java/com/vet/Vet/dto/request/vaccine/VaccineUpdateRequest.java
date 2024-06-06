package com.vet.Vet.dto.request.vaccine;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineUpdateRequest {
    @Positive(message = "ID değeri pozitif bir değer olmalı.")
    private int id;
    @NotNull(message = "Aşı ismi boş bırakılamaz.")
    private String name;
    @NotNull(message = "Aşı kodu boş bırakılamaz.")
    private String code;
    @NotNull
    private int animalId;
    private int reportId;
}
