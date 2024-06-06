package com.vet.Vet.api;

import com.vet.Vet.buisness.abstracts.IAnimalService;
import com.vet.Vet.core.exception.NotFoundException;
import com.vet.Vet.core.result.Result;
import com.vet.Vet.core.result.ResultData;
import com.vet.Vet.core.utilies.ResultHelper;
import com.vet.Vet.dto.request.animal.AnimalSaveRequest;
import com.vet.Vet.dto.request.animal.AnimalUpdateRequest;
import com.vet.Vet.dto.response.CursorResponse;
import com.vet.Vet.dto.response.animal.AnimalResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {
    private final IAnimalService animalService;

    public AnimalController(IAnimalService animalService) {
        this.animalService = animalService;
    }

    //Değerlendirme Formu 12
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        return ResultHelper.created(this.animalService.save(animalSaveRequest));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id") int id) {
        return ResultHelper.success(this.animalService.get(id));
    }
    //Değerlendirme Formu 13
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getByName(@PathVariable("name") String name) {
        return ResultHelper.success(this.animalService.getByName(name));
    }
    //Değerlendirme Formu 14
    @GetMapping("/customer/{customerName}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getByCustomerName(@PathVariable("customerName") String name) {
        return ResultHelper.success(this.animalService.getByCustomerName(name));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return ResultHelper.cursor(this.animalService.cursor(page, pageSize));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {
        this.animalService.getOne(animalUpdateRequest.getId());
        return ResultHelper.success(this.animalService.update(animalUpdateRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.animalService.delete(id);
        return ResultHelper.ok();
    }

}
