package com.maua.yegestaodesaude.modules.bloodPressure.create_blood_pressure.app;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.dtos.BloodPressureDTO;
import com.maua.yegestaodesaude.shared.domain.entities.BloodPressure;
import com.maua.yegestaodesaude.shared.domain.entities.Client;
import com.maua.yegestaodesaude.shared.domain.repositories.BloodPressureRepository;
import com.maua.yegestaodesaude.shared.domain.repositories.ClientRepository;

@Service
public class CreateBloodPressureUsecase {

    @Autowired
    private BloodPressureRepository bloodPressureRepository;

    @Autowired
    private ClientRepository clientRepository;

    public ResponseEntity<Object> execute(BloodPressureDTO bloodPressureDTO, Long clientId){
        
        Client client = this.clientRepository.findById(clientId).orElseThrow(() -> {
            throw new RuntimeException("Cliente não encontrado!");
        });
        
        Date sqlDate = Date.valueOf(bloodPressureDTO.date());
        BloodPressure bloodPressure = new BloodPressure();
        
        bloodPressure.setClient(client);
        bloodPressure.setDate(sqlDate);
        
        String patternMeasure = "\\d{1,3}x\\d{1,3}";
        Pattern regexPattern = Pattern.compile(patternMeasure);
        Matcher matcher = regexPattern.matcher(bloodPressureDTO.measure());

        if(!matcher.matches()){
            return ResponseEntity.status(422).body(new CreateBloodPressureViewmodel("Medida inválida!"));
        }
        bloodPressure.setMeasure(bloodPressureDTO.measure());
        String level = bloodPressure.levelPressure(bloodPressureDTO.measure());
        bloodPressure.setLevel(level);

        this.bloodPressureRepository.save(bloodPressure);

        if(bloodPressure.getId() == null){
            return ResponseEntity.status(400).body(new CreateBloodPressureViewmodel("Erro ao salvar pressão arterial!"));
        }
        return ResponseEntity.status(201).body(new CreateBloodPressureViewmodel("Criado com sucesso!"));
    }    
}
