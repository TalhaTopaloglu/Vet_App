package com.vet.Vet.dto.request.animal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalSaveRequest {
    @NotNull(message = "Name boş bırakılamaz")
    private String name;
    @NotNull(message = "Species boş bırakılamaz")
    private String species;
    @NotNull(message = "Breed boş bırakılamaz")
    private String breed;
    @NotNull(message = "Gender boş bırakılamaz")
    private String gender;
    @NotNull(message = "Colour boş bırakılamaz")
    private String colour;
    @NotNull(message = "Birth Day boş bırakılamaz")
    private LocalDate dateOfBirth;
    @NotNull(message = "MÜşteri bilgisi boş bırakılamaz")
    @NotNull
    private int customerId;
}
