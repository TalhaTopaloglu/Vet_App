package com.vet.Vet.buisness.abstracts;

import com.vet.Vet.dto.request.availableDate.AvailableDateSaveRequest;
import com.vet.Vet.dto.request.availableDate.AvailableDateUpdateRequest;
import com.vet.Vet.dto.response.availableDate.AvailableDateResponse;
import com.vet.Vet.entities.AvailableDate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IAvailableDateService {
    AvailableDate getOne(int id);
    AvailableDateResponse save(AvailableDateSaveRequest availableDateSaveRequest);
    AvailableDateResponse get(int id);
    Page<AvailableDateResponse> cursor(int page, int pageSize);
    AvailableDateResponse update(AvailableDateUpdateRequest availableDateUpdateRequest);
    boolean delete(int id);
    List<LocalDate> localDatesByDoctorId(int id);
}
