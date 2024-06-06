package com.vet.Vet.dao;

import com.vet.Vet.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

//    @Query("SELECT c FROM Customer c WHERE c.name ILIKE ?1")
//    List<Customer> findByName(String name);

    List<Customer> findByNameContainingIgnoreCase(String name);

    Optional<Customer> findByNameAndMail(String name, String mail);
}

