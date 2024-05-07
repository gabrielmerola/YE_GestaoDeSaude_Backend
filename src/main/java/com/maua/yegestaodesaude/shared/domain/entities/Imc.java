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
    private String imc;
    private String level;
    private Date date;

    public String ImcLevel(Double imc){
        if(imc < 18.5){
            return "Abaixo do peso";
        } else if(imc >= 18.5 && imc < 24.9){
            return "Peso normal";
        } else if(imc >= 25 && imc < 29.9){
            return "Sobrepeso";
        } else if(imc >= 30 && imc < 34.9){
            return "Obesidade grau 1";
        } else if(imc >= 35 && imc < 39.9){
            return "Obesidade grau 2";
        } else {
            return "Obesidade grave";
        }
    }
}
