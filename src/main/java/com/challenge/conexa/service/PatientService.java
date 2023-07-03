package com.challenge.conexa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.challenge.conexa.models.dto.PatientDTO;
import com.challenge.conexa.models.entity.Patient;
import com.challenge.conexa.repository.PatientRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PatientService {
    private PatientRepository patientRepository;

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
    
    public void incrementPatientAppointments(Long id) {
        patientRepository.incrementPatientAppointments(id);
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient update(PatientDTO patientDto) {
        Optional<Patient> patientOpt = patientRepository.findById(patientDto.getId());
        if(!patientOpt.isPresent()){
            return new Patient();
        }

        var patient = patientOpt.get();
        patient.setName(patientDto.getName());
        patient.setAddress(patientDto.getAddress());
        patient.setPhone(patientDto.getPhone());
        
        return patientRepository.save(patient);

    }

    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

}