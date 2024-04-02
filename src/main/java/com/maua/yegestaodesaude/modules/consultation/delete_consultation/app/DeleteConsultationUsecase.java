package com.maua.yegestaodesaude.modules.consultation.delete_consultation.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.maua.yegestaodesaude.shared.domain.repositories.ConsultationRepository;


@Service
public class DeleteConsultationUsecase {

    @Autowired
    private ConsultationRepository consultationRepository;

    public ResponseEntity<Object> execute(Long consultationId) {
        try {
            consultationRepository.deleteById(consultationId);
            return ResponseEntity.status(200)
                    .body(DeleteConsultationViewmodel.builder().message("Consulta excluída com sucesso").build());
        } catch (Exception e) {
            return ResponseEntity.status(400)
                    .body(DeleteConsultationViewmodel.builder().message("Erro ao excluir consulta").build());
        }
    }
}