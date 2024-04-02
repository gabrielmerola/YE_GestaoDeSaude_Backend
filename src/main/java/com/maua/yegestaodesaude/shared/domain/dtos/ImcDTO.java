package com.maua.yegestaodesaude.shared.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record ImcDTO(
    @Schema(example = "12.9")
    Double weight,
    @Schema(example = "1.8")
    Double height,
    @Schema(example = "2012-12-12")
    String date
){
}
