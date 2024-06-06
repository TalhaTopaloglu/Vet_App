package com.vet.Vet.dao;


import com.vet.Vet.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine, Integer> {
    List<Vaccine> findByNameAndCodeAndAnimalId(String name, String code, int animalId);
    Optional<Vaccine> findByNameAndCodeAndAnimalIdAndProtectionStartDateAndProtectionFinishDate(String name, String code, int animalId, LocalDate protectionStartDate,LocalDate protectionFinishDate);
    @Query("SELECT v FROM Vaccine v WHERE v.protectionFinishDate >= ?1 AND v.protectionFinishDate <= ?2")
    List<Vaccine> findByProtectionFinishDate(LocalDate startDate,LocalDate endDate);
    @Query("SELECT v FROM Vaccine v WHERE v.animal.id = ?1")
    List<Vaccine> findVaccineByAnimalId(int id);

}
