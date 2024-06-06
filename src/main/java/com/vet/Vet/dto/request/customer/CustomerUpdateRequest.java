package com.vet.Vet.dto.request.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateRequest {
    @Positive(message = "ID değeri pozitif bir değer olmalı.")
    private int id;
    @NotNull(message = "Müşteri ismi boş bırakılamaz.")
    private String name;
    @NotNull(message = "Müşteri telefon numarası boş bırakılamaz.")
    private String phone;
    @Email(message = "Geçerli bir mail adresi giriniz.")
    private String mail;
    @NotNull(message = "Müşterinin adresi boş bırakılamaz.")
    private String address;
    @NotNull(message = "Müşterinin şehir boş bırakılamaz.")
    private String city;

}
