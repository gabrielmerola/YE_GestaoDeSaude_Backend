package com.maua.yegestaodesaude.modules.glucose.get_glucose.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.entities.Glucose;
import com.maua.yegestaodesaude.shared.domain.repositories.GlucoseRepository;
import com.maua.yegestaodesaude.shared.utils.DateUtils;

@Service
public class GetGlucoseUsecase {

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private GlucoseRepository glucoseRepository;
    
    public ResponseEntity<Object> execute(Long clientId) {
        List<Glucose> glucose = glucoseRepository.findAllByClientId(clientId);

        if(glucose.isEmpty()){
            return ResponseEntity.status(204).body("{\"message\": \"Não encontrado\"}");
        }

        List<GetGlucoseViewmodel> glucoseList = new ArrayList<>();

        for (Glucose gc : glucose) {
            glucoseList.add(GetGlucoseViewmodel.builder()
                .id(gc.getId())
                .date(dateUtils.RevertDate(gc.getDate()))
                .measure(gc.getMeasure())
                .level(gc.getLevel())
                .build()
            );
        }

        int n = glucoseList.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (glucoseList.get(j).getId() > glucoseList.get(j + 1).getId()) {
                    // Trocar os elementos
                    GetGlucoseViewmodel temp = glucoseList.get(j);
                    glucoseList.set(j, glucoseList.get(j + 1));
                    glucoseList.set(j + 1, temp);
                    swapped = true;
                }
            }
            // Se não houve troca na última passada, a lista está ordenada
            if (!swapped) break;
        }

        return ResponseEntity.status(200).body(glucoseList);
    }
}
