package com.vet.Vet.api;

import com.vet.Vet.buisness.abstracts.IDoctorService;
import com.vet.Vet.core.result.Result;
import com.vet.Vet.core.result.ResultData;
import com.vet.Vet.core.utilies.ResultHelper;
import com.vet.Vet.dto.request.doctor.DoctorSaveRequest;
import com.vet.Vet.dto.request.doctor.DoctorUpdateRequest;
import com.vet.Vet.dto.response.CursorResponse;
import com.vet.Vet.dto.response.doctor.DoctorResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/doctors")
public class DoctorController {

    private final IDoctorService doctorService;

    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }
    //Değerlendirme Formu 15
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> save(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest) {
        return ResultHelper.created(this.doctorService.save(doctorSaveRequest));
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> get(@PathVariable("id") int id){
        return ResultHelper.success(this.doctorService.get(id));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<DoctorResponse>> cursor(
            @RequestParam(name = "page" , required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize" , required = false,defaultValue = "10") int pageSize
    ) {
        return ResultHelper.cursor(this.doctorService.cursor(page, pageSize));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest) {
        this.doctorService.getOne(doctorUpdateRequest.getId());
        return ResultHelper.success(this.doctorService.update(doctorUpdateRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.doctorService.delete(id);
        return ResultHelper.ok();
    }

}
