package com.maua.yegestaodesaude.modules.bloodPressure.get_blood_pressure.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.entities.BloodPressure;
import com.maua.yegestaodesaude.shared.domain.repositories.BloodPressureRepository;

@Service
public class GetBloodPressureUsecase {
    
    @Autowired
    private BloodPressureRepository bloodPressureRepository;

    public ResponseEntity<Object> execute(Long clientId){
        List<BloodPressure> bloodPressure = bloodPressureRepository.findAllByClientId(clientId);

        if(bloodPressure.isEmpty()){
            return ResponseEntity.status(204).body("{\"message\": \"Não encontrado\"}");
        }

        List<GetBloodPressureViewmodel> bloodPressureList = new ArrayList<>();
        
        for (BloodPressure bp : bloodPressure) {
            bloodPressureList.add(GetBloodPressureViewmodel.builder()
                .id(bp.getId())
                .date(bp.getDate())
                .measure(bp.getMeasure())
                .level(bp.getLevel())
                .build()
            );
        }
        return ResponseEntity.status(200).body(bloodPressureList);
    }
}
