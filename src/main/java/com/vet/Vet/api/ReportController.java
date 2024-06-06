package com.vet.Vet.api;

import com.vet.Vet.buisness.abstracts.IReportService;
import com.vet.Vet.core.result.Result;
import com.vet.Vet.core.result.ResultData;
import com.vet.Vet.core.utilies.ResultHelper;
import com.vet.Vet.dto.request.report.ReportSaveRequest;
import com.vet.Vet.dto.request.report.ReportUpdateRequest;
import com.vet.Vet.dto.response.CursorResponse;
import com.vet.Vet.dto.response.report.ReportResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/report")
public class ReportController {

    private final IReportService reportService;

    public ReportController(IReportService reportService){
        this.reportService = reportService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<ReportResponse> save(@Valid @RequestBody ReportSaveRequest reportSaveRequest) {
        return ResultHelper.created(this.reportService.save(reportSaveRequest));
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<ReportResponse> get(@PathVariable("id") int id){
        return ResultHelper.success(this.reportService.get(id));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<ReportResponse>> cursor(
            @RequestParam(name = "page" , required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize" , required = false,defaultValue = "10") int pageSize
    ) {
        return ResultHelper.cursor(this.reportService.cursor(page, pageSize));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<ReportResponse> update(@Valid @RequestBody ReportUpdateRequest reportUpdateRequest) {
        this.reportService.getOne(reportUpdateRequest.getId());
        return ResultHelper.success(this.reportService.update(reportUpdateRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.reportService.delete(id);
        return ResultHelper.ok();
    }
}
