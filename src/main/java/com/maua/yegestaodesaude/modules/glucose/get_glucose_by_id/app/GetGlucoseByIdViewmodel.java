package com.maua.yegestaodesaude.modules.glucose.get_glucose_by_id.app;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetGlucoseByIdViewmodel {
    private Long id;
    private Date date;
    private Integer measure;
    private String level;
}
