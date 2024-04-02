package com.maua.yegestaodesaude.shared.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record BloodPressureDTO (
    @Schema(example = "2021-10-10")
    String date,
    @Schema(example = "120x80")
    String measure
){
}
