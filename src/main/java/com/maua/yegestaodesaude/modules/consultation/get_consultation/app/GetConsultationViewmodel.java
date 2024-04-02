package com.maua.yegestaodesaude.modules.consultation.get_consultation.app;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetConsultationViewmodel {

    private Long id;
    private String name;
    private Date date;
    private String expertise;
    private Date dateReturn;
    private String description;

}
