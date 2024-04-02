package com.maua.yegestaodesaude.shared.domain.entities;

import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "medicine")
public class Medicine {
    
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Client client;

    private String name;
    private Time time;
    private Integer period;
    private Integer quantity;
    private String dosage;
}
