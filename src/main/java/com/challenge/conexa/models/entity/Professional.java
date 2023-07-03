package com.challenge.conexa.models.entity;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
@Entity
@Table(name = "professional")
public class Professional {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String crm;

    @OneToMany(
        mappedBy = "professional",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonIgnore
    private List<Appointment> appointments;

    @CreationTimestamp
    private ZonedDateTime date_created;

}