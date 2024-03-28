package com.maua.yegestaodesaude.shared.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetClientResponseDTO {

    private String phone;
    private String email;
    private String name;
    private String id;
    private String cpf;

}