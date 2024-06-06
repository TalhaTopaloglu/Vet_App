package com.vet.Vet.buisness.abstracts;

import com.vet.Vet.dto.request.appointment.AppointmentSaveRequest;
import com.vet.Vet.dto.request.appointment.AppointmentUpdateRequest;
import com.vet.Vet.dto.response.appointment.AppointmentResponse;
import com.vet.Vet.entities.Animal;
import com.vet.Vet.entities.Appointment;
import com.vet.Vet.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IAppointmentService {
    Appointment getOne(int id);
    AppointmentResponse save(AppointmentSaveRequest appointmentSaveRequest);
    AppointmentResponse get(int id);
    Page<AppointmentResponse> cursor(int page, int pageSize);
    AppointmentResponse update(AppointmentUpdateRequest appointmentUpdateRequest);
    List<AppointmentResponse> getDoctorsAppointment(LocalDate startDate, LocalDate finishDate, Doctor doctor);
    List<AppointmentResponse> getAnimalAppointments(LocalDate startDate, LocalDate finishDate, Animal animal);

    boolean delete(int id);
}
