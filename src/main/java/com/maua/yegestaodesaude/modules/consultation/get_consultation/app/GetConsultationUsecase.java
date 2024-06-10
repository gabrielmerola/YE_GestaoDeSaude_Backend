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

        int n = consultationList.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (consultationList.get(j).getId() > consultationList.get(j + 1).getId()) {
                    // Trocar os elementos
                    GetConsultationViewmodel temp = consultationList.get(j);
                    consultationList.set(j, consultationList.get(j + 1));
                    consultationList.set(j + 1, temp);
                    swapped = true;
                }
            }
            // Se não houve troca na última passada, a lista está ordenada
            if (!swapped) break;
        }

        return ResponseEntity.status(200).body(consultationList);
    }
}
