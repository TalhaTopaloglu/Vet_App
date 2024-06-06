package com.vet.Vet.buisness.abstracts;

import com.vet.Vet.dto.request.doctor.DoctorSaveRequest;
import com.vet.Vet.dto.request.doctor.DoctorUpdateRequest;
import com.vet.Vet.dto.response.doctor.DoctorResponse;
import com.vet.Vet.entities.Animal;
import com.vet.Vet.entities.AvailableDate;
import com.vet.Vet.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IDoctorService {
    Doctor getOne(int id);
    DoctorResponse save(DoctorSaveRequest doctorSaveRequest);
    DoctorResponse get(int id);
    Page<DoctorResponse> cursor(int page, int pageSize);
    DoctorResponse update(DoctorUpdateRequest doctorUpdateRequest);
    boolean delete(int id);
    List<Doctor> getAll();
    List<AvailableDate> doctorAvailableDates(int id);
}
