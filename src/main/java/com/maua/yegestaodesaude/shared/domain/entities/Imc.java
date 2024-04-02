package com.maua.yegestaodesaude.shared.domain.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "imc")
public class Imc {
    
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Client client;

    private Double weight;
    private Double height;
    private Double imc;
    private String level;
    private Date date;
}
