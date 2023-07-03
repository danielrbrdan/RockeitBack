package com.challenge.conexa.models.dto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import com.challenge.conexa.models.entity.Patient;
import com.challenge.conexa.models.entity.Professional;

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
