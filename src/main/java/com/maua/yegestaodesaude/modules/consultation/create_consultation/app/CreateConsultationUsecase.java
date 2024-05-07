package com.maua.yegestaodesaude.modules.consultation.create_consultation.app;

import com.maua.yegestaodesaude.shared.domain.dtos.ConsultationDTO;
import com.maua.yegestaodesaude.shared.domain.entities.Client;
import com.maua.yegestaodesaude.shared.domain.entities.Consultation;
import com.maua.yegestaodesaude.shared.domain.repositories.ClientRepository;
import com.maua.yegestaodesaude.shared.domain.repositories.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

            if(consultationDTO.name().isEmpty() || consultationDTO.name() == null){
                return ResponseEntity.status(422).body(new CreateConsultationViewmodel("Nome da consulta não pode ser vazio"));
            }
            if(consultationDTO.expertise().isEmpty() || consultationDTO.expertise() == null){
                return ResponseEntity.status(422).body(new CreateConsultationViewmodel("Especialidade da consulta não pode ser vazio"));
            }
            if(consultationDTO.date().isEmpty() || consultationDTO.date() == null){
                return ResponseEntity.status(422).body(new CreateConsultationViewmodel("Data da consulta não pode ser vazio"));
            }
            if(consultationDTO.dateReturn().isEmpty() || consultationDTO.dateReturn() == null){
                return ResponseEntity.status(422).body(new CreateConsultationViewmodel("Data de retorno da consulta não pode ser vazio"));
            }
            if(consultationDTO.description().isEmpty() || consultationDTO.description() == null){
                return ResponseEntity.status(422).body(new CreateConsultationViewmodel("Descrição da consulta não pode ser vazio"));
            }

            String regexDate = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";

            if(!consultationDTO.date().matches(regexDate)){
                return ResponseEntity.status(422).body(new CreateConsultationViewmodel("Data da consulta inválida"));
            }
            if(!consultationDTO.dateReturn().matches(regexDate)){
                return ResponseEntity.status(422).body(new CreateConsultationViewmodel("Data de retorno da consulta inválida"));
            }

            Consultation consultation = new Consultation();
            consultation.setClient(client);
            consultation.setName(consultationDTO.name());
            consultation.setExpertise(consultationDTO.expertise());
            consultation.setDate(consultationDTO.date());
            consultation.setDateReturn(consultationDTO.dateReturn());
            consultation.setDescription(consultationDTO.description());

            consultation = consultationRepository.save(consultation);

            return ResponseEntity.status(201).body(new CreateConsultationViewmodel("Consulta criada com sucesso"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CreateConsultationViewmodel(e.getMessage()));
        }
    }
}
