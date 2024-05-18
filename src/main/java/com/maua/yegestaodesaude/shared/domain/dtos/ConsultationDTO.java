package com.maua.yegestaodesaude.shared.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record ConsultationDTO(
        @Schema(example = "Clinico Geral")
        String name,
        @Schema(example = "Clinico Geral")
        String expertise,
        @Schema(example = "2021-10-10")
        String date,
        @Schema(example = "2021-10-10")
        String dateReturn,
        @Schema(example = "Descrição da consulta")
        String description
) {
}
