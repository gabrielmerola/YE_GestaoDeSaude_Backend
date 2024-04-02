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
@Table(name = "glucose")
public class Glucose {
    
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Client client;

    private Date date;
    private String measure;
    private String level;
}
