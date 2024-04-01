package com.maua.yegestaodesaude.shared.domain.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "blood_pressure")
@Data
public class BloodPressure {

    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    private Client client;

    private Date date;
    private String measure;
    private String level;

    public String levelPressure(String measure){
        String[] measures = measure.split("x");
        int systolic = Integer.parseInt(measures[0]);
        int diastolic = Integer.parseInt(measures[1]);
        if(systolic < 90 && diastolic < 60){
            return "Baixa";
        } else if(systolic < 120 && diastolic < 80){
            return "Ótima";
        } else if((systolic > 120 && systolic < 129) && (diastolic > 80 && diastolic < 84)){
            return "Normal";
        } else if((systolic > 130 && systolic < 139) && (diastolic > 85 && diastolic < 89)){
            return "Atenção";
        } else {
            return "Alta";
        }
    }
}
