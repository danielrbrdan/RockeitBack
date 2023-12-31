package com.challenge.rockeit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.challenge.rockeit.models.dto.ProfessionalDTO;
import com.challenge.rockeit.models.entity.Patient;
import com.challenge.rockeit.models.entity.Professional;
import com.challenge.rockeit.repository.AppointmentRepository;
import com.challenge.rockeit.repository.ProfessionalRepository;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class ProfessionalService {
    private ProfessionalRepository professionalRepository;
    
    public List<Professional> findAll() {
        return professionalRepository.findAll();
    }

    public Professional save(Professional professional) {
        return professionalRepository.save(professional);
    }

    public Optional<Professional> findById(Long professionalId) {
        return professionalRepository.findById(professionalId);
    }

    public Professional update(ProfessionalDTO professionalDto) {
        Optional<Professional> professionalOpt = professionalRepository.findById(professionalDto.getId());
        if(!professionalOpt.isPresent()){
            return new Professional();
        }

        var professional = professionalOpt.get();
        professional.setName(professionalDto.getName());
        professional.setCrm(professionalDto.getCrm());
        
        return professionalRepository.save(professional);
    }

    public void deleteById(Long id) {
        professionalRepository.deleteById(id);
    }

}
