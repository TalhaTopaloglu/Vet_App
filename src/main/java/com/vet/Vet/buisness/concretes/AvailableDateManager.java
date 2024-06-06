package com.vet.Vet.buisness.concretes;

import com.vet.Vet.buisness.abstracts.IAvailableDateService;
import com.vet.Vet.buisness.abstracts.IDoctorService;
import com.vet.Vet.core.config.modelMapper.IModelMapperService;
import com.vet.Vet.core.exception.AlreadyExistException;
import com.vet.Vet.core.exception.NotFoundException;
import com.vet.Vet.core.utilies.Msg;
import com.vet.Vet.dao.AvailableDateRepo;
import com.vet.Vet.dto.request.availableDate.AvailableDateSaveRequest;
import com.vet.Vet.dto.request.availableDate.AvailableDateUpdateRequest;
import com.vet.Vet.dto.response.availableDate.AvailableDateResponse;
import com.vet.Vet.entities.AvailableDate;
import com.vet.Vet.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepo availableDateRepo;
    private final IModelMapperService modelMapper;
    private final IDoctorService doctorService;

    public AvailableDateManager(AvailableDateRepo availableDateRepo, IModelMapperService modelMapper, IDoctorService doctorService) {
        this.availableDateRepo = availableDateRepo;
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }

    @Override
    public AvailableDate getOne(int id) {
        return this.availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public AvailableDateResponse save(AvailableDateSaveRequest availableDateSaveRequest) {
        Optional<AvailableDate> isAvailableDateExist = availableDateRepo.findByAvailableDateAndDoctorId(availableDateSaveRequest.getAvailableDate(), availableDateSaveRequest.getDoctorId());
        if (isAvailableDateExist.isPresent()) {
            throw new AlreadyExistException("Veri zaten kayıt edilmiş");
        }

        AvailableDate saveAvailableDate = this.modelMapper.forRequest().map(availableDateSaveRequest, AvailableDate.class);
        saveAvailableDate.setId(0);
        Doctor doctor = doctorService.getOne(availableDateSaveRequest.getDoctorId());
        saveAvailableDate.setDoctor(doctor);
        this.availableDateRepo.save(saveAvailableDate);
        return this.modelMapper.forResponse().map(saveAvailableDate, AvailableDateResponse.class);

    }

    @Override
    public AvailableDateResponse get(int id) {
        AvailableDate availableDate = this.getOne(id);
        return this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class);
    }

    @Override
    public Page<AvailableDateResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<AvailableDate> availableDatePage = this.availableDateRepo.findAll(pageable);
        return availableDatePage.map(availableDate -> this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class));
    }

    @Override
    public AvailableDateResponse update(AvailableDateUpdateRequest availableDateUpdateRequest) {
        Optional<AvailableDate> isAvailableDateExist = availableDateRepo.findByAvailableDateAndDoctorId(availableDateUpdateRequest.getAvailableDate(), availableDateUpdateRequest.getDoctorId());
        if (isAvailableDateExist.isPresent()) {
            throw new AlreadyExistException("Veri zaten kayıt edilmiş");
        }
        AvailableDate updateAvailableDate = this.modelMapper.forRequest().map(availableDateUpdateRequest, AvailableDate.class);
        Doctor doctor = doctorService.getOne(availableDateUpdateRequest.getDoctorId());
        updateAvailableDate.setDoctor(doctor);
        this.availableDateRepo.save(updateAvailableDate);
        return this.modelMapper.forResponse().map(availableDateUpdateRequest, AvailableDateResponse.class);

    }

    @Override
    public boolean delete(int id) {
        AvailableDate availableDate = this.getOne(id);
        this.availableDateRepo.delete(availableDate);
        return true;
    }

    // Doctor entity'sinin id'sini kullanara doktorun müsait günlerini alıp.Bir listede tutuyoruz.
    @Override
    public List<LocalDate> localDatesByDoctorId(int id) {
        Doctor doctor = doctorService.getOne(id);
        List<AvailableDate> doctorAvailableDate = doctor.getAvailableDateList();
        List<LocalDate> localDateList = new ArrayList<>();
        for(AvailableDate date : doctorAvailableDate){
            localDateList.add(date.getAvailableDate());
        }
        return localDateList;
    }
}
