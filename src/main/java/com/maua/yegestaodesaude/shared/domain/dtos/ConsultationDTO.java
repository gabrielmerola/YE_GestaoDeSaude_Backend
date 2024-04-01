package com.maua.yegestaodesaude.shared.domain.dtos;

public record ConsultationDTO (
        String name,
        String expertise,
        String date,
        String dateReturn,
        String description) {
}
