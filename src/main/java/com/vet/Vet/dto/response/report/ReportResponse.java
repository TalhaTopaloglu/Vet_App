package com.vet.Vet.dto.response.report;

import com.vet.Vet.entities.Appointment;
import com.vet.Vet.entities.Vaccine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {
    private int id;
    private String description;
    private double price;
    private List<Vaccine> vaccineList;
    private Appointment appointment;
}
