package com.maua.yegestaodesaude.shared.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record ClientDto(
        @Schema(example = "João da Silva")
        String name,
        @Schema(example = "Exemplo@gmail.com")
        String email,
        @Schema(example = "Teste@01")
        String password,
        @Schema(example = "11999999999")
        String phone,
        @Schema(example = "12345678901")
        String cpf
) {
}
