package com.vet.Vet.dto.response.availableDate;

import com.vet.Vet.entities.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateResponse {
    private int id;
    private LocalDate availableDate;
    private int doctorId;
}
