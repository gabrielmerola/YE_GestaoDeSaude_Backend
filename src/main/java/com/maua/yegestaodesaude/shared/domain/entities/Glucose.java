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
    private Integer measure;
    private String level;

    public String levelGlucose(Integer measure) {
        if(measure < 70){
            return "Baixo";
        } else if(measure >= 70 && measure < 100){
            return "Normal";
        } else if(measure >= 100 && measure < 126){
            return "Atenção";
        } else {
            return "Alta";
        }
    }
}
