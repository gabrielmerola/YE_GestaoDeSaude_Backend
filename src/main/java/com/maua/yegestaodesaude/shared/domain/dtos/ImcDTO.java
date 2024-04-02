package com.maua.yegestaodesaude.shared.domain.dtos;

import java.sql.Date;

public record ImcDTO(
    Double weight,
    Double height,
    Date date
){
}
