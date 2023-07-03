package com.challenge.rockeit.models.dto;

import java.util.List;


import com.challenge.rockeit.models.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Long id;
    private String name;
    private Long phone;
    private Long totalAppointment;
    private Address address;
}
