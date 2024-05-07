package com.maua.yegestaodesaude.modules.consultation.get_consultation_by_id.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetConsultationByIdViewmodel {

    private Long id;
    private String name;
    private String date;
    private String expertise;
    private String dateReturn;
    private String description;
}
