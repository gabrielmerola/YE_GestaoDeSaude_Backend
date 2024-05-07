package com.maua.yegestaodesaude.modules.consultation.get_consultation.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.entities.Consultation;
import com.maua.yegestaodesaude.shared.domain.repositories.ConsultationRepository;
import com.maua.yegestaodesaude.shared.utils.DateUtils;

@Service
public class GetConsultationUsecase {

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private ConsultationRepository consultationRepository;

    public ResponseEntity<Object> execute(Long clientId) {
        List<Consultation> consultation = consultationRepository.findAllByClientId(clientId);

        if (consultation.isEmpty()) {
            return ResponseEntity.status(204).body("{\"message\": \"Não encontrado\"}");
        }

        List<GetConsultationViewmodel> consultationList = new ArrayList<>();

        for (Consultation gc : consultation) {
            consultationList.add(GetConsultationViewmodel.builder()
                    .id(gc.getId())
                    .date(dateUtils.RevertDateString(gc.getDate()))
                    .name(gc.getName())
                    .expertise(gc.getExpertise())
                    .dateReturn(dateUtils.RevertDateString(gc.getDateReturn()))
                    .description(gc.getDescription())
                    .build());
        }
        return ResponseEntity.status(200).body(consultationList);
    }
}
