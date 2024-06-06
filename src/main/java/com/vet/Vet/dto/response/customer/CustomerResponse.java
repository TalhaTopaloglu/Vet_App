package com.vet.Vet.dto.response.customer;

import com.vet.Vet.entities.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    private int id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
    private List<Animal> animalList;
}
