package com.maua.yegestaodesaude.modules.bloodPressure.get_latest_blood_pressure.app;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetLatestBloodPressureViewmodel {
    private Long id;
    private Date date;
    private String measure;
    private String level;
}
