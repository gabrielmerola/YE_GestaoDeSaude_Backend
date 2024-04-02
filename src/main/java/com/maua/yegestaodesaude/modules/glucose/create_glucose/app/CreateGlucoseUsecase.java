package com.maua.yegestaodesaude.modules.glucose.create_glucose.app;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.dtos.GlucoseDTO;
import com.maua.yegestaodesaude.shared.domain.entities.Client;
import com.maua.yegestaodesaude.shared.domain.entities.Glucose;
import com.maua.yegestaodesaude.shared.domain.repositories.ClientRepository;
import com.maua.yegestaodesaude.shared.domain.repositories.GlucoseRepository;

@Service
public class CreateGlucoseUsecase {
    
    @Autowired
    private GlucoseRepository glucoseRepository;

    @Autowired
    private ClientRepository clientRepository;

    public ResponseEntity<Object> execute(GlucoseDTO glucoseDTO, Long clientId) {

        Client client = this.clientRepository.findById(clientId).orElseThrow(() -> {
            throw new RuntimeException("Cliente não encontrado!");
        });

        String date = glucoseDTO.date();
        Date sqlDate = Date.valueOf(date);
        Glucose glucose = new Glucose();

        glucose.setClient(client);
        glucose.setDate(sqlDate);

        glucose.setMeasure(glucoseDTO.measure());

        String level = glucose.levelGlucose(glucoseDTO.measure());
        glucose.setLevel(level);

        this.glucoseRepository.save(glucose);

        if(glucose.getId() == null){
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(new CreateGlucoseViewmodel("Erro ao salvar glicose!"));
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(new CreateGlucoseViewmodel("glicose criada com sucesso"));
    }
}
