package com.maua.yegestaodesaude.modules.laboratoryTest.get_all_laboratory_test.app;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetAllLaboratoryTestViewmodel {

    private Long id;
    private String textName;
    private String gender;
    private int rangeRefMin;
    private int rangeRefMax;
    private String unitOfMeasurement;
    
}
