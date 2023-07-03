package com.challenge.rockeit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.challenge.rockeit.models.dto.AppointmentDTO;
import com.challenge.rockeit.models.entity.Appointment;
import com.challenge.rockeit.models.entity.Patient;
import com.challenge.rockeit.models.entity.User;
import com.challenge.rockeit.repository.AppointmentRepository;
import com.challenge.rockeit.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;
    private final ProfessionalService professionalService;

    public Appointment save(Appointment appointment) throws Exception {
        if (appointmentRepository.existsByDateTimeAndProfessionalId(
                appointment.getDateTime(), appointment.getProfessional().getId()
            )
        ) {
            throw new Exception("ProfessionalAlreadyInUseException");
        }

        Patient patient = patientService.findById(appointment.getPatient().getId()).get();
        appointment.setPatient(patient);

        appointment.setProfessional(
            professionalService.findById(
                appointment.getProfessional().getId()
            ).get()
        );

        
        patientService.incrementPatientAppointments(patient.getId());

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }

    public void deleteAll(List<Appointment> appointments) {
        appointmentRepository.deleteAll(appointments);
    }

    public Appointment update(AppointmentDTO appointmentDto) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(appointmentDto.getId());
        if(!appointmentOpt.isPresent()){
            return new Appointment();
        }

        var appointment = appointmentOpt.get();
        appointment.setObservation(appointmentDto.getObservation());
        
        return appointmentRepository.save(appointment);
    }

    

}