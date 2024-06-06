package com.vet.Vet.dto.request.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportSaveRequest {
    private String description;
    private double price;
    private int appointment_id;
}
