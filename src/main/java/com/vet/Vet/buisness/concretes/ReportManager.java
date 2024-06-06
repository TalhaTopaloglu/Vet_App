package com.vet.Vet.buisness.concretes;

import com.vet.Vet.buisness.abstracts.IAppointmentService;
import com.vet.Vet.buisness.abstracts.IReportService;
import com.vet.Vet.core.config.modelMapper.IModelMapperService;
import com.vet.Vet.core.exception.AlreadyExistException;
import com.vet.Vet.core.exception.NotFoundException;
import com.vet.Vet.core.utilies.Msg;
import com.vet.Vet.dao.ReportRepo;
import com.vet.Vet.dto.request.report.ReportSaveRequest;
import com.vet.Vet.dto.request.report.ReportUpdateRequest;
import com.vet.Vet.dto.response.animal.AnimalResponse;
import com.vet.Vet.dto.response.report.ReportResponse;
import com.vet.Vet.entities.Animal;
import com.vet.Vet.entities.Appointment;
import com.vet.Vet.entities.Customer;
import com.vet.Vet.entities.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportManager implements IReportService {

    private final ReportRepo reportRepo;
    private final IAppointmentService appointmentService;
    private final IModelMapperService modelMapper;

    public ReportManager(ReportRepo reportRepo, IAppointmentService appointmentService, IModelMapperService modelMapper){
        this.reportRepo = reportRepo;
        this.appointmentService = appointmentService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Report getOne(int id) {
        return this.reportRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public ReportResponse save(ReportSaveRequest reportSaveRequest) {
        Optional<Report> checkReport = reportRepo.findByAppointmentId(
                reportSaveRequest.getAppointment_id()
        );
        if(checkReport.isPresent()){
            throw new AlreadyExistException("Veri zaten mevcut");
        }

        Report saveReport = this.modelMapper.forRequest().map(reportSaveRequest, Report.class);
        saveReport.setId(0);
        Appointment appointment = appointmentService.getOne(reportSaveRequest.getAppointment_id());
        saveReport.setAppointment(appointment);
        this.reportRepo.save(saveReport);
        return this.modelMapper.forResponse().map(saveReport, ReportResponse.class);
    }

    @Override
    public ReportResponse get(int id) {
        Report report = this.reportRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return this.modelMapper.forResponse().map(report, ReportResponse.class);
    }

    @Override
    public Page<ReportResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        Page<Report> reportPage = this.reportRepo.findAll(pageable);
        return reportPage.map(report -> this.modelMapper.forResponse().map(report, ReportResponse.class));
    }

    @Override
    public ReportResponse update(ReportUpdateRequest reportUpdateRequest) {
        Report updateReport = this.modelMapper.forRequest().map(reportUpdateRequest, Report.class);
        Appointment appointment = appointmentService.getOne(reportUpdateRequest.getAppointment_id());
        updateReport.setAppointment(appointment);
        this.reportRepo.save(updateReport);
        return this.modelMapper.forResponse().map(updateReport, ReportResponse.class);

    }

    @Override
    public boolean delete(int id) {
       Report report = this.getOne(id);
       this.reportRepo.delete(report);
       return true;
    }
}
