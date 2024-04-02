package com.maua.yegestaodesaude.shared.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record MedicineDTO (
    @Schema(example = "Dipirona")
    String name,
    @Schema(example = "08:00:00")
    String time,
    @Schema(example = "6")
    Integer period,
    @Schema(example = "2")
    Integer quantity,
    @Schema(example = "1 comprimido de 500mg")
    String dosage
){
}
