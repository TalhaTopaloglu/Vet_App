package com.vet.Vet.dto.request.doctor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorUpdateRequest {
    @Positive(message = "ID değeri pozitif bir değer olmalı.")
    @NotNull
    private int id;
    @NotNull
    private String name;
    private String phone;
    @NotNull
    private String mail;
    @NotNull
    private String address;
    @NotNull
    private String city;
}
