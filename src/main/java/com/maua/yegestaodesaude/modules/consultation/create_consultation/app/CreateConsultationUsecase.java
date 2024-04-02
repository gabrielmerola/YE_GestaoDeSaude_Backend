package com.maua.yegestaodesaude.modules.consultation.create_consultation.app;

import com.maua.yegestaodesaude.shared.domain.dtos.ConsultationDTO;
import com.maua.yegestaodesaude.shared.domain.entities.Client;
import com.maua.yegestaodesaude.shared.domain.entities.Consultation;
import com.maua.yegestaodesaude.shared.domain.repositories.ClientRepository;
import com.maua.yegestaodesaude.shared.domain.repositories.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class CreateConsultationUsecase {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private ClientRepository clientRepository;

    public ResponseEntity<Object> execute(ConsultationDTO consultationDTO, Long clientId) {
        try {
            Client client = this.clientRepository.findById(clientId).orElseThrow(() -> {
                throw new RuntimeException("Cliente não encontrado!");
            });

            Date sqlDate = Date.valueOf(consultationDTO.date());
            Date sqlDateReturn = Date.valueOf(consultationDTO.dateReturn());

            Consultation consultation = new Consultation();
            consultation.setClient(client);
            consultation.setName(consultationDTO.name());
            consultation.setExpertise(consultationDTO.expertise());
            consultation.setDate(sqlDate);
            consultation.setDateReturn(sqlDateReturn);
            consultation.setDescription(consultationDTO.description());

            consultation = consultationRepository.save(consultation);

            return ResponseEntity.status(201).body(new CreateConsultationViewmodel("Consulta criada com sucesso"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CreateConsultationViewmodel(e.getMessage()));
        }
    }
}
