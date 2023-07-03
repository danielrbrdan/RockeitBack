package com.challenge.conexa.controller;

import java.text.ParseException;
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

import com.challenge.conexa.models.dto.AppointmentDTO;
import com.challenge.conexa.models.dto.PatientDTO;
import com.challenge.conexa.models.entity.Appointment;
import com.challenge.conexa.service.AppointmentService;
import com.challenge.conexa.utils.Mapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final Mapper mapper;

    @GetMapping()
    public ResponseEntity<List<AppointmentDTO>> findAll() {
        List<AppointmentDTO> appointmentsDTO = 
            this.mapper.mapList(appointmentService.findAll(), AppointmentDTO.class);
        
        return ResponseEntity.ok().body(appointmentsDTO);
    }

    @PostMapping()
    public ResponseEntity<AppointmentDTO> save(@RequestBody Appointment appointment) throws Exception {
        return ResponseEntity.ok(
            mapper.map(
                appointmentService.save(appointment), 
                AppointmentDTO.class
            )
        );
    }
   
    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws Exception {
        appointmentService.deleteById(id);
    }

    @PutMapping()
    public ResponseEntity<AppointmentDTO> update(@RequestBody AppointmentDTO appointment) {
        return ResponseEntity.ok().body(
            this.mapper.map(appointmentService.update(appointment), AppointmentDTO.class)
        );
    }
}
