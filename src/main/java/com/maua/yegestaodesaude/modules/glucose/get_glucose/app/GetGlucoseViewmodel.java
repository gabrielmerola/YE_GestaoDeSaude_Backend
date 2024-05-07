package com.maua.yegestaodesaude.modules.glucose.get_glucose.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetGlucoseViewmodel {
    private Long id;
    private String date;
    private Integer measure;
    private String level;
}
