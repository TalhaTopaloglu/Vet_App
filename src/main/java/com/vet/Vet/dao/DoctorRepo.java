package com.vet.Vet.dao;

import com.vet.Vet.entities.Animal;
import com.vet.Vet.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Integer> {

    Optional<Doctor> findByNameAndMail(String name, String mail);
}
