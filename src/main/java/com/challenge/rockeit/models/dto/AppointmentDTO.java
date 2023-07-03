package com.challenge.rockeit.models.dto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import com.challenge.rockeit.models.entity.Patient;
import com.challenge.rockeit.models.entity.Professional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private Long id;
    private Professional professional;
    private Patient patient;

    private LocalDateTime dateTime;
    
    private ZonedDateTime date_created;

    private String observation;
}
