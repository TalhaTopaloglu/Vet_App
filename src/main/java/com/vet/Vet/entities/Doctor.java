package com.vet.Vet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id", columnDefinition = "serial")
    private int id;
    @Column(name = "doctor_name")
    private String name;
    @Column(name = "doctor_phone")
    private String phone;
    @Column(name = "doctor_mail")
    private String mail;
    @Column(name = "doctor_address")
    private String address;
    @Column(name = "doctor_city")
    private String city;
    //Değerlendirme Formu 9
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Appointment> appointmentList;
    //Değerlendirme Formu 9
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AvailableDate> availableDateList;
}
