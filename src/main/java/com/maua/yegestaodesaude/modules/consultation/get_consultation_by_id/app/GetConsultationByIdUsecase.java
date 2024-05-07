package com.maua.yegestaodesaude.modules.consultation.get_consultation_by_id.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.maua.yegestaodesaude.shared.domain.entities.Consultation;
import com.maua.yegestaodesaude.shared.domain.repositories.ConsultationRepository;
import com.maua.yegestaodesaude.shared.utils.DateUtils;

@Service
public class GetConsultationByIdUsecase {

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private ConsultationRepository consultationRepository;

    public ResponseEntity<Object> execute(Long consultationId) {
        Optional<Consultation> consultationOptional = consultationRepository.findById(consultationId);

        if (consultationOptional.isPresent()) {
            Consultation consultation = consultationOptional.get();
            GetConsultationByIdViewmodel viewModel = GetConsultationByIdViewmodel.builder()
                    .id(consultation.getId())
                    .name(consultation.getName())
                    .date(dateUtils.RevertDateString(consultation.getDate()))
                    .expertise(consultation.getExpertise())
                    .dateReturn(dateUtils.RevertDateString(consultation.getDateReturn()))
                    .description(consultation.getDescription())
                    .build();
            return ResponseEntity.status(200).body(viewModel);
        } else {
            return ResponseEntity.status(204).body("{\"message\": \"Consulta não encontrada\"}");
        }
    }
}
