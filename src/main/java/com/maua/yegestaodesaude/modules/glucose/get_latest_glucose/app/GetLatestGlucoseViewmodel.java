package com.maua.yegestaodesaude.modules.glucose.get_latest_glucose.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetLatestGlucoseViewmodel {
    private Long id;
    private String date;
    private String measure;
    private String level;
}
