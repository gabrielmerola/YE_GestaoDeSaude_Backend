package com.maua.yegestaodesaude.shared.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UpdateClientDTO {
    @Schema(example = "exemplo@gmail.com")
    private String email;
    @Schema(example = "Teste@01")
    private String newPassword;
}
