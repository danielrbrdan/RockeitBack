package com.challenge.rockeit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.challenge.rockeit.models.entity.Professional;

@Repository
public interface ProfessionalRepository extends CrudRepository<Professional, Long> {
    List<Professional> findAll();
}
