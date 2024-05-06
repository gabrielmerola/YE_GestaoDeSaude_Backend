package com.maua.yegestaodesaude.modules.imc.get_date_imc.app;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetDateImcViewmodel {
    private Date date;
}
