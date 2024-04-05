package com.maua.yegestaodesaude.modules.Medicine.get_medicine_by_id.app;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetMedicineByIdViewmodel {

    private String name;
    private Time time;
    private Integer period;
    private Integer quantity;
    private String dosage;
    
}
