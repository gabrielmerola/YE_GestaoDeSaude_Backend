package com.maua.yegestaodesaude.modules.consultation.create_consultation.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.dtos.ConsultationDTO;
import com.maua.yegestaodesaude.shared.domain.entities.Client;
import com.maua.yegestaodesaude.shared.domain.entities.Consultation;
import com.maua.yegestaodesaude.shared.domain.repositories.ClientRepository;
import com.maua.yegestaodesaude.shared.domain.repositories.ConsultationRepository;

import java.sql.Date;

@Service
public class CreateConsultationUsecase {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private ClientRepository clientRepository;

    public ResponseEntity<Object> execute(ConsultationDTO consultationDTO, Long clientId) {
        try {
            System.out.println("Iniciando execução do caso de uso para criar consulta.");

            // Verifica se o cliente existe
            Client client = this.clientRepository.findById(clientId).orElseThrow(() -> {
                throw new RuntimeException("Cliente não encontrado!");
            });
            System.out.println("Cliente encontrado: " + client.getName());

            // Converte as datas do DTO para o formato SQL Date
            Date sqlDate = Date.valueOf(consultationDTO.date());
            Date sqlDateReturn = Date.valueOf(consultationDTO.dateReturn());

            // Cria uma nova consulta
            Consultation consultation = new Consultation();
            consultation.setClient(client);
            consultation.setName(consultationDTO.name());
            consultation.setExpertise(consultationDTO.expertise());
            consultation.setDate(sqlDate);
            consultation.setDateReturn(sqlDateReturn);
            consultation.setDescription(consultationDTO.description());

            // Salva a consulta no repositório
            consultation = consultationRepository.save(consultation);
            System.out.println("Consulta salva com sucesso no repositório.");

            // Retorna a resposta
            return ResponseEntity.ok(consultation);
        } catch (Exception e) {
            System.err.println("Erro ao executar caso de uso para criar consulta: " + e.getMessage());
            throw new RuntimeException("Erro ao criar consulta.", e);
        }
    }

}