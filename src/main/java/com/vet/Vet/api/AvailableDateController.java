package com.vet.Vet.api;

import com.vet.Vet.buisness.abstracts.IAvailableDateService;
import com.vet.Vet.core.result.Result;
import com.vet.Vet.core.result.ResultData;
import com.vet.Vet.core.utilies.ResultHelper;
import com.vet.Vet.dto.request.availableDate.AvailableDateSaveRequest;
import com.vet.Vet.dto.request.availableDate.AvailableDateUpdateRequest;
import com.vet.Vet.dto.response.CursorResponse;
import com.vet.Vet.dto.response.availableDate.AvailableDateResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/available-dates")
public class AvailableDateController {

    private final IAvailableDateService availableDateService;

    public AvailableDateController(IAvailableDateService availableDateService) {
        this.availableDateService = availableDateService;
    }

    //Değerlendirme Formu 16
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest) {
        return ResultHelper.created(this.availableDateService.save(availableDateSaveRequest));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> get(@PathVariable("id") int id) {
        return ResultHelper.success(this.availableDateService.get(id));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AvailableDateResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        return ResultHelper.cursor(this.availableDateService.cursor(page, pageSize));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest) {
        this.availableDateService.getOne(availableDateUpdateRequest.getId());
        return ResultHelper.success(this.availableDateService.update(availableDateUpdateRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.availableDateService.delete(id);
        return ResultHelper.ok();
    }

}
