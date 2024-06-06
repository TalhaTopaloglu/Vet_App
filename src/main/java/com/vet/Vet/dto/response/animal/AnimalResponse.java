package com.vet.Vet.dto.response.animal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vet.Vet.entities.Appointment;
import com.vet.Vet.entities.Customer;
import com.vet.Vet.entities.Vaccine;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {
    private int id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;
    private Customer customer;
    private List<Vaccine> vaccineList;
    private List<Appointment> appointmentList;
}
