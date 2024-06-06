package com.vet.Vet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", columnDefinition = "serial")
    private int id;

    @Column(name = "report_description")
    private String description;

    @Column(name = "report_price")
    private double price;

    @OneToMany (mappedBy = "report", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Vaccine> vaccineList;

    @OneToOne
    @JoinColumn (name = "appointment_id")
    private Appointment appointment;

}
