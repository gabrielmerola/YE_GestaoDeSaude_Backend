package com.maua.yegestaodesaude.modules.imc.create_imc.app;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.dtos.ImcDTO;
import com.maua.yegestaodesaude.shared.domain.entities.Client;
import com.maua.yegestaodesaude.shared.domain.entities.Imc;
import com.maua.yegestaodesaude.shared.domain.repositories.ClientRepository;
import com.maua.yegestaodesaude.shared.domain.repositories.ImcRepository;

@Service
public class CreateImcUsecase {
    
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ImcRepository imcRepository;

    public ResponseEntity<Object> execute(ImcDTO imcDTO, Long clientId) {
        Client client = this.clientRepository.findById(clientId).orElseThrow(() -> {
            throw new RuntimeException("Cliente não encontrado!");
        });

        String date = imcDTO.date();
        Date sqlDate = Date.valueOf(date);
        Imc imc = new Imc();

        imc.setClient(client);
        imc.setDate(sqlDate);
        imc.setHeight(imcDTO.height());
        imc.setWeight(imcDTO.weight());

        Double imcValue = imcDTO.weight() / (imcDTO.height() * imcDTO.height());
        imc.setImc(imcValue);

        String level = imc.ImcLevel(imcValue);
        imc.setLevel(level);

        this.imcRepository.save(imc);

        if(imc.getId() == null){
            return ResponseEntity.status(400).body(new CreateImcViewmodel("Erro ao salvar IMC!"));
        }
        return ResponseEntity.status(201).body(new CreateImcViewmodel("IMC criado com sucesso!"));
    }
}
