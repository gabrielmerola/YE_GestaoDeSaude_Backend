package com.maua.yegestaodesaude.modules.bloodPressure.get_blood_pressure.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.entities.BloodPressure;
import com.maua.yegestaodesaude.shared.domain.repositories.BloodPressureRepository;
import com.maua.yegestaodesaude.shared.utils.DateUtils;

@Service
public class GetBloodPressureUsecase {
    
    @Autowired
    private DateUtils dateUtils;

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
                .date(dateUtils.RevertDate(bp.getDate()))
                .measure(bp.getMeasure())
                .level(bp.getLevel())
                .build()
            );
        }

        int n = bloodPressureList.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (bloodPressureList.get(j).getId() > bloodPressureList.get(j + 1).getId()) {
                    // Trocar os elementos
                    GetBloodPressureViewmodel temp = bloodPressureList.get(j);
                    bloodPressureList.set(j, bloodPressureList.get(j + 1));
                    bloodPressureList.set(j + 1, temp);
                    swapped = true;
                }
            }
            // Se não houve troca na última passada, a lista está ordenada
            if (!swapped) break;
        }

        return ResponseEntity.status(200).body(bloodPressureList);
    }
}
