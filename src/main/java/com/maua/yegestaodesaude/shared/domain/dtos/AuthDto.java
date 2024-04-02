package com.maua.yegestaodesaude.shared.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthDto(
    @Schema(example = "exemplo@gmail.com")
    String email,
    @Schema(example = "Teste@01")
    String password
) {
}
