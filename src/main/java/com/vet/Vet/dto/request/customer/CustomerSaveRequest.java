package com.vet.Vet.dto.request.customer;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest {
    @NotNull(message = "Müşteri ismi boş bırakılamaz.")
    private String name;
    @NotNull(message = "Müşteri telefon numarası boş bırakılamaz.")
    private String phone;
    @NotNull(message = "Müşteri maili boş bırakılamaz.")
    @Email(message = "Geçerli bir mail adresi giriniz.")
    private String mail;
    @NotNull(message = "Müşterinin adresi boş bırakılamaz.")
    private String address;
    @NotNull(message = "Müşterinin şehir boş bırakılamaz.")
    private String city;
}
