
package com.maua.yegestaodesaude.modules.bloodPressure.get_blood_pressure_by_id;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GetBloodPressureByIdViewmodel {

    private Date date;
    private String measure;
    private String level;

}
