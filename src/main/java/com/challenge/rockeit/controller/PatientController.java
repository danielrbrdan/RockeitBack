package com.challenge.rockeit.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.rockeit.models.dto.PatientDTO;
import com.challenge.rockeit.models.entity.Patient;
import com.challenge.rockeit.service.PatientService;
import com.challenge.rockeit.utils.Mapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/patient")
public class PatientController {
    private final PatientService patientService;
    
    private final Mapper mapper;

    @GetMapping()
    public ResponseEntity<List<PatientDTO>> findAll() {
        List<PatientDTO> patientsDTO = 
            this.mapper.mapList(patientService.findAll(), PatientDTO.class);
            
        return ResponseEntity.ok().body(patientsDTO);
    }

    @PostMapping()
    public ResponseEntity<PatientDTO> save(@RequestBody Patient patient) {
        return ResponseEntity.ok().body(
            this.mapper.map(patientService.save(patient), PatientDTO.class)
        );
    }

    @PutMapping()
    public ResponseEntity<PatientDTO> update(@RequestBody PatientDTO patient) {
        return ResponseEntity.ok().body(
            this.mapper.map(patientService.update(patient), PatientDTO.class)
        );
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws Exception {
        patientService.deleteById(id);
    }
}
