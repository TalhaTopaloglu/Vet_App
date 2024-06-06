package com.vet.Vet.buisness.abstracts;

import com.vet.Vet.dto.request.animal.AnimalSaveRequest;
import com.vet.Vet.dto.request.animal.AnimalUpdateRequest;
import com.vet.Vet.dto.request.report.ReportSaveRequest;
import com.vet.Vet.dto.request.report.ReportUpdateRequest;
import com.vet.Vet.dto.response.animal.AnimalResponse;
import com.vet.Vet.dto.response.report.ReportResponse;
import com.vet.Vet.entities.Report;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReportService {

    Report getOne(int id);
    ReportResponse save(ReportSaveRequest reportSaveRequest);
    ReportResponse get(int id);
    Page<ReportResponse> cursor(int page, int pageSize);
    ReportResponse update(ReportUpdateRequest reportUpdateRequest);
    boolean delete(int id);
}
