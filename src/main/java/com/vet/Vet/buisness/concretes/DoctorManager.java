package com.vet.Vet.buisness.concretes;

import com.vet.Vet.buisness.abstracts.IDoctorService;
import com.vet.Vet.core.config.modelMapper.IModelMapperService;
import com.vet.Vet.core.exception.AlreadyExistException;
import com.vet.Vet.core.exception.NotFoundException;
import com.vet.Vet.core.utilies.Msg;
import com.vet.Vet.dao.DoctorRepo;
import com.vet.Vet.dto.request.doctor.DoctorSaveRequest;
import com.vet.Vet.dto.request.doctor.DoctorUpdateRequest;
import com.vet.Vet.dto.response.doctor.DoctorResponse;
import com.vet.Vet.entities.AvailableDate;
import com.vet.Vet.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class DoctorManager implements IDoctorService {
    private final DoctorRepo doctorRepo;
    private final IModelMapperService modelMapper;

    public DoctorManager(DoctorRepo doctorRepo, IModelMapperService modelMapper) {
        this.doctorRepo = doctorRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Doctor getOne(int id) {
        return this.doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public DoctorResponse save(DoctorSaveRequest doctorSaveRequest) {

        Optional<Doctor> checkDoctor = this.doctorRepo.findByNameAndMail(
                doctorSaveRequest.getName(),
                doctorSaveRequest.getMail()
        );

        if (checkDoctor.isPresent()){
            throw new AlreadyExistException("Veri zaten mevcut");
        }

        Doctor saveDoctor = this.modelMapper.forRequest().map(doctorSaveRequest, Doctor.class);
        this.doctorRepo.save(saveDoctor);
        return this.modelMapper.forResponse().map(saveDoctor,DoctorResponse.class);
    }

    @Override
    public DoctorResponse get(int id) {
        Doctor doctor = this.doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return this.modelMapper.forResponse().map(doctor,DoctorResponse.class);
    }

    @Override
    public Page<DoctorResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        Page<Doctor> doctorPage = this.doctorRepo.findAll(pageable);
        return doctorPage.map(doctor -> this.modelMapper.forResponse().map(doctor, DoctorResponse.class));
    }

    @Override
    public DoctorResponse update(DoctorUpdateRequest doctorUpdateRequest) {
        Doctor updateDoctor = this.modelMapper.forRequest().map(doctorUpdateRequest,Doctor.class);
        Doctor doctor = this.getOne(doctorUpdateRequest.getId());
        updateDoctor.setAvailableDateList(doctor.getAvailableDateList());
        updateDoctor.setAppointmentList(doctor.getAppointmentList());
        this.doctorRepo.save(updateDoctor);
        return this.modelMapper.forResponse().map(updateDoctor,DoctorResponse.class);
    }

    @Override
    public boolean delete(int id) {
        Doctor doctor = this.getOne(id);
        this.doctorRepo.delete(doctor);
        return true;
    }

    public List<Doctor> getAll(){
        return this.doctorRepo.findAll();
    }

    @Override
    public List<AvailableDate> doctorAvailableDates(int id) {
        return this.getOne(id).getAvailableDateList();
    }

}
