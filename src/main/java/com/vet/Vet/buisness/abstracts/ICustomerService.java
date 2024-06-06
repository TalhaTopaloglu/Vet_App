package com.vet.Vet.buisness.abstracts;

import com.vet.Vet.dto.request.customer.CustomerSaveRequest;
import com.vet.Vet.dto.request.customer.CustomerUpdateRequest;
import com.vet.Vet.dto.response.customer.CustomerResponse;
import com.vet.Vet.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerService {
    Customer getOne(int id);
    List<CustomerResponse> getByName(String name);
    CustomerResponse save(CustomerSaveRequest customerSaveRequest);
    CustomerResponse get(int id);
    Page<CustomerResponse> cursor(int page, int pageSize);
    CustomerResponse update(CustomerUpdateRequest customerUpdateRequest);
    boolean delete(int id);
}
