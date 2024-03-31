package com.maua.yegestaodesaude.shared.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UpdateClientDTO {
    private String email;
    private String newPassword;
}
