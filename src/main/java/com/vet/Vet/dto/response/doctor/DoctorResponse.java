package com.vet.Vet.dto.response.doctor;

import com.vet.Vet.entities.Appointment;
import com.vet.Vet.entities.AvailableDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {
    private int id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
    private List<Appointment> appointmentList;
    private List<AvailableDate> availableDateList;
}
