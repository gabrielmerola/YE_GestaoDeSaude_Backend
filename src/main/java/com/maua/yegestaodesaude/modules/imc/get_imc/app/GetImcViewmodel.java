package com.maua.yegestaodesaude.modules.imc.get_imc.app;

import java.sql.Date;

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
    private Double imc;
    private String level;
    private Date date;
}
