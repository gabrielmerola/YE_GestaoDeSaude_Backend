package com.maua.yegestaodesaude.shared.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "laboratory_test")
@Data
public class LaboratoryTest {
    
    @Id
    @GeneratedValue(generator = "increment")
    private Long id;

    private String textName;
    private String gender;
    private int rangeRefMin;
    private int rangeRefMax;
    private String unitOfMeasurement;
}
