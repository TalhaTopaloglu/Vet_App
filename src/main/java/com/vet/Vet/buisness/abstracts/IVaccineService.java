package com.vet.Vet.buisness.abstracts;

import com.vet.Vet.dto.request.vaccine.VaccineSaveRequest;
import com.vet.Vet.dto.request.vaccine.VaccineUpdateRequest;
import com.vet.Vet.dto.response.vaccine.VaccineResponse;
import com.vet.Vet.entities.Animal;
import com.vet.Vet.entities.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IVaccineService {
    Vaccine getOne(int id);
    List<VaccineResponse> getByAnimalId(int id);
    List<VaccineResponse> getByProtectionFinishDate(LocalDate startDate,LocalDate endDate);
    VaccineResponse save(VaccineSaveRequest vaccineSaveRequest);
    VaccineResponse get(int id);
    Page<VaccineResponse> cursor(int page, int pageSize);
    VaccineResponse update(VaccineUpdateRequest vaccineUpdateRequest);
    boolean delete(int id);
}
