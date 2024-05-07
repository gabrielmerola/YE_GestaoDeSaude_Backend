package com.maua.yegestaodesaude.modules.consultation.get_consultation.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetConsultationViewmodel {

    private Long id;
    private String name;
    private String date;
    private String expertise;
    private String dateReturn;
    private String description;

}
