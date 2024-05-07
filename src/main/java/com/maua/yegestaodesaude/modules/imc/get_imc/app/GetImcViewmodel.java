package com.maua.yegestaodesaude.modules.imc.get_imc.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetImcViewmodel {

    private Long id;
    private Double weight;
    private Double height;
    private String imc;
    private String level;
    private String date;
}
