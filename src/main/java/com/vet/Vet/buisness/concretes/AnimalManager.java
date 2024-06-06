package com.vet.Vet.buisness.concretes;

import com.vet.Vet.buisness.abstracts.IAnimalService;
import com.vet.Vet.buisness.abstracts.ICustomerService;
import com.vet.Vet.core.config.modelMapper.IModelMapperService;
import com.vet.Vet.core.exception.AlreadyExistException;
import com.vet.Vet.core.exception.NotFoundException;
import com.vet.Vet.core.utilies.Msg;;
import com.vet.Vet.core.utilies.ResultHelper;
import com.vet.Vet.dao.AnimalRepo;
import com.vet.Vet.dto.request.animal.AnimalSaveRequest;
import com.vet.Vet.dto.request.animal.AnimalUpdateRequest;
import com.vet.Vet.dto.response.animal.AnimalResponse;
import com.vet.Vet.entities.Animal;
import com.vet.Vet.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AnimalManager implements IAnimalService {

    private final AnimalRepo animalRepo;
    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;

    public AnimalManager(AnimalRepo animalRepo, ICustomerService customerService, IModelMapperService modelMapper) {
        this.animalRepo = animalRepo;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Animal getOne(int id) {
        return this.animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public AnimalResponse save(AnimalSaveRequest animalSaveRequest) {

        Optional<Animal> checkAnimal = animalRepo.findByNameAndSpeciesAndCustomerId(
                animalSaveRequest.getName(),
                animalSaveRequest.getSpecies(),
                animalSaveRequest.getCustomerId()
        );

        if (checkAnimal.isPresent()) {
            throw new AlreadyExistException("Veri zaten mevcut");
        }

        Animal saveAnimal = this.modelMapper.forRequest().map(animalSaveRequest, Animal.class);
        saveAnimal.setId(0);
        Customer customer = customerService.getOne(animalSaveRequest.getCustomerId());
        saveAnimal.setCustomer(customer);
        this.animalRepo.save(saveAnimal);
        return this.modelMapper.forResponse().map(saveAnimal, AnimalResponse.class);
    }

    @Override
    public AnimalResponse get(int id) {
        Animal animal = this.animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return this.modelMapper.forResponse().map(animal, AnimalResponse.class);
    }

    @Override
    public List<AnimalResponse> getByName(String name) {

        List<AnimalResponse> animalResponseList = animalRepo.findByNamesLetter(name)
                .stream()
                .map(animal -> modelMapper.forResponse().map(animal, AnimalResponse.class)
                ).collect(Collectors.toList());

        if(animalResponseList.isEmpty()){
            throw new NotFoundException(name + " isimli hayvan bulunmamaktadır");
        }
        return animalResponseList;
    }

    @Override
    public List<AnimalResponse> getByCustomerName(String name) {

        List<AnimalResponse> animalResponseList=  animalRepo.findAnimalsByCustomerName(name)
                .stream()
                .map(animal -> modelMapper.forResponse().map(animal, AnimalResponse.class)
                ).collect(Collectors.toList());

        if(animalResponseList.isEmpty()){
            throw new NotFoundException(name + " isimli müşteri bulunmamaktadır");
        }
        return animalResponseList;
    }

    @Override
    public Page<AnimalResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Animal> animalPage = this.animalRepo.findAll(pageable);
        return animalPage.map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class));
    }

    @Override
    public AnimalResponse update(AnimalUpdateRequest animalUpdateRequest) {
        Animal updateAnimal = this.modelMapper.forRequest().map(animalUpdateRequest, Animal.class);
        Customer customer = customerService.getOne(animalUpdateRequest.getCustomerId());
        // updateAnimal.setCustomer(animalUpdateRequest.getCustomer());
        updateAnimal.setCustomer(customer);

        this.animalRepo.save(updateAnimal);
        return this.modelMapper.forResponse().map(updateAnimal, AnimalResponse.class);
    }

    @Override
    public boolean delete(int id) {
        Animal animal = this.getOne(id);
        this.animalRepo.delete(animal);
        return true;
    }
}
