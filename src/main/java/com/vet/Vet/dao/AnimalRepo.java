package com.vet.Vet.dao;

import com.vet.Vet.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Integer> {
    @Query("SELECT a FROM Animal a WHERE a.name ILIKE ?1")
    List<Animal> findByNamesLetter(String name);
    @Query("SELECT a FROM Animal a WHERE a.customer.name ILIKE ?1")
    List<Animal> findAnimalsByCustomerName(String name);
    Optional<Animal> findByNameAndSpeciesAndCustomerId(String name, String species, int customerId);
}
