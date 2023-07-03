package com.challenge.conexa.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.conexa.models.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>  {

    List<Appointment> findAllByProfessionalId(Long id);

    List<Appointment> findAllByProfessionalIdAndDateTime(Long id, String date);

    void deleteById(Long id);

    boolean existsByDateTimeAndProfessionalId(LocalDateTime time, Long professionalId);

}
