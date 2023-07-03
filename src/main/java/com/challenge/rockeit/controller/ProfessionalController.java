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
import org.springframework.web.bind.annotation.RestController;

import com.challenge.rockeit.models.dto.PatientDTO;
import com.challenge.rockeit.models.dto.ProfessionalDTO;
import com.challenge.rockeit.models.entity.Patient;
import com.challenge.rockeit.models.entity.Professional;
import com.challenge.rockeit.service.ProfessionalService;
import com.challenge.rockeit.utils.Mapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/professional")
public class ProfessionalController {
    private final ProfessionalService professionalService;
    private final Mapper mapper;

    @GetMapping()
    public ResponseEntity<List<ProfessionalDTO>> findAll() {
        return ResponseEntity.ok().body(
            this.mapper.mapList(professionalService.findAll(), ProfessionalDTO.class)
        );
    }

    @PostMapping
    public ResponseEntity<ProfessionalDTO> save(@RequestBody Professional professional) {
         ProfessionalDTO professionalDTO = 
            this.mapper.map(professionalService.save(professional), ProfessionalDTO.class);
            
        return ResponseEntity.ok().body(professionalDTO);
    }

    @PutMapping()
    public ResponseEntity<ProfessionalDTO> update(@RequestBody ProfessionalDTO professional) {
        return ResponseEntity.ok().body(
            this.mapper.map(professionalService.update(professional), ProfessionalDTO.class)
        );
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws Exception {
        professionalService.deleteById(id);
    }
   
}
