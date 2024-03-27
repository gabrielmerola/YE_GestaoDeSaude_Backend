package com.maua.yegestaodesaude.shared.domain.dtos;

public record ClientDto(
        String name,
        String email,
        String password,
        String phone,
        String cpf
) {
}
