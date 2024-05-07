package com.maua.yegestaodesaude.modules.glucose.get_glucose_by_id.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetGlucoseByIdViewmodel {
    private Long id;
    private String date;
    private Integer measure;
    private String level;
}
