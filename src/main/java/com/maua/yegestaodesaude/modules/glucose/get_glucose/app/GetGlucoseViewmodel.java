package com.maua.yegestaodesaude.modules.glucose.get_glucose.app;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetGlucoseViewmodel {
    private Long id;
    private Date date;
    private Integer measure;
    private String level;
}
