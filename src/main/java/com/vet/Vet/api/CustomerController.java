package com.vet.Vet.api;


import com.vet.Vet.buisness.abstracts.ICustomerService;
import com.vet.Vet.core.exception.NotFoundException;
import com.vet.Vet.core.result.Result;
import com.vet.Vet.core.result.ResultData;
import com.vet.Vet.core.utilies.ResultHelper;
import com.vet.Vet.dto.request.customer.CustomerSaveRequest;
import com.vet.Vet.dto.request.customer.CustomerUpdateRequest;
import com.vet.Vet.dto.response.CursorResponse;
import com.vet.Vet.dto.response.customer.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {
    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    //Değerlendirme Formu 10
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest) {
        return ResultHelper.created(this.customerService.save(customerSaveRequest));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> get(@PathVariable("id") int id) {
        return ResultHelper.success(this.customerService.get(id));
    }

    //Değerlendirme Formu 11
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<CustomerResponse>> getByName(@PathVariable("name") String name) {
        return ResultHelper.success(this.customerService.getByName(name));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CustomerResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return ResultHelper.cursor(this.customerService.cursor(page, pageSize));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        this.customerService.getOne(customerUpdateRequest.getId());
        return ResultHelper.success(this.customerService.update(customerUpdateRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.customerService.delete(id);
        return ResultHelper.ok();
    }

}




























