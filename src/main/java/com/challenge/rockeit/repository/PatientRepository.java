package com.challenge.rockeit.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.challenge.rockeit.models.entity.Patient;


@Repository
public interface PatientRepository extends CrudRepository<Patient, Long>{
    List<Patient> findAll();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE patient set total_appointment = total_appointment + 1 WHERE id = :id",
    nativeQuery=true)
    Integer incrementPatientAppointments(Long id);
}