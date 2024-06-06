package com.vet.Vet.buisness.abstracts;

import com.vet.Vet.dto.request.animal.AnimalSaveRequest;
import com.vet.Vet.dto.request.animal.AnimalUpdateRequest;
import com.vet.Vet.entities.Animal;
import com.vet.Vet.dto.response.animal.AnimalResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IAnimalService {
    Animal getOne(int id);
    List<AnimalResponse> getByName(String name);
    List<AnimalResponse> getByCustomerName(String name);
    AnimalResponse save(AnimalSaveRequest animalSaveRequest);
    AnimalResponse get(int id);
    Page<AnimalResponse> cursor(int page, int pageSize);
    AnimalResponse update(AnimalUpdateRequest animalUpdateRequest);
    boolean delete(int id);


}
