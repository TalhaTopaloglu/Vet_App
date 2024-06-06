package com.vet.Vet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "available_dates")
public class AvailableDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_date_id", columnDefinition = "serial")
    private int id;
    @Column(name = "available_date")
    private LocalDate availableDate;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "available_date_doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;

}
