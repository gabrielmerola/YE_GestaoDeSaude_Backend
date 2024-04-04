package com.maua.yegestaodesaude.modules.imc.get_imc_by_id.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetImcByIdViewmodel {
    private Long id;
    private Double weight;
    private Double height;
    private Double imc;
    private String level;
    private String date;
}
