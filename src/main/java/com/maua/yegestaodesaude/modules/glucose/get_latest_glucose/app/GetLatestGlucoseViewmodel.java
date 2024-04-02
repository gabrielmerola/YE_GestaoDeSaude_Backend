package com.maua.yegestaodesaude.modules.glucose.get_latest_glucose.app;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetLatestGlucoseViewmodel {
    private Long id;
    private Date date;
    private String measure;
    private String level;
}
