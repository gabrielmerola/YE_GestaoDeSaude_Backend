package com.maua.yegestaodesaude.shared.domain.dtos;

public record UsuarioDto(
        String name,
        String email,
        String password,
        String phone
) {
}
