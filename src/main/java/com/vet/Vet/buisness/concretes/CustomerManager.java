package com.vet.Vet.buisness.concretes;

import com.vet.Vet.buisness.abstracts.ICustomerService;
import com.vet.Vet.core.config.modelMapper.IModelMapperService;
import com.vet.Vet.core.exception.AlreadyExistException;
import com.vet.Vet.core.exception.NotFoundException;
import com.vet.Vet.core.utilies.Msg;
import com.vet.Vet.core.utilies.ResultHelper;
import com.vet.Vet.dao.CustomerRepo;
import com.vet.Vet.dto.request.customer.CustomerSaveRequest;
import com.vet.Vet.dto.request.customer.CustomerUpdateRequest;
import com.vet.Vet.dto.response.customer.CustomerResponse;
import com.vet.Vet.entities.Customer;
import com.vet.Vet.entities.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerManager implements ICustomerService {

    private final CustomerRepo customerRepo;
    private final IModelMapperService modelMapper;

    public CustomerManager(CustomerRepo customerRepo, IModelMapperService modelMapper) {
        this.customerRepo = customerRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Customer getOne(int id) {
        return this.customerRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public List<CustomerResponse> getByName(String name) {

        List<CustomerResponse> customerList =  customerRepo.findByNameContainingIgnoreCase(name)
                .stream()
                .map(customer -> modelMapper.forResponse().map(customer, CustomerResponse.class)
                ).collect(Collectors.toList());

        if (customerList.isEmpty()) {
            throw new NotFoundException(name + " isimli müşteri kaydı yok!");
        }
        return customerList;
    }

    @Override
    public CustomerResponse save(CustomerSaveRequest customerSaveRequest) {
        Optional<Customer> checkCustomer = customerRepo.findByNameAndMail(
                customerSaveRequest.getName(),
                customerSaveRequest.getMail()
        );

        if(checkCustomer.isPresent()){
            throw new AlreadyExistException("Veri zaten mevcut");
        }

        Customer saveCustomer = this.modelMapper.forRequest().map(customerSaveRequest, Customer.class);
        this.customerRepo.save(saveCustomer);
        return this.modelMapper.forResponse().map(saveCustomer, CustomerResponse.class);
    }

    @Override
    public CustomerResponse get(int id) {
        Customer customer = this.customerRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return this.modelMapper.forResponse().map(customer, CustomerResponse.class);
    }

    @Override
    public Page<CustomerResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Customer> customerPage = this.customerRepo.findAll(pageable);
        return customerPage.map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class));
    }

    @Override
    public CustomerResponse update(CustomerUpdateRequest customerUpdateRequest) {
        Customer updateCustomer = this.modelMapper.forRequest().map(customerUpdateRequest, Customer.class);
        this.customerRepo.save(updateCustomer);
        return this.modelMapper.forResponse().map(updateCustomer, CustomerResponse.class);
    }

    @Override
    public boolean delete(int id) {
        Customer customer = this.getOne(id);
        this.customerRepo.delete(customer);
        return true;
    }
}
