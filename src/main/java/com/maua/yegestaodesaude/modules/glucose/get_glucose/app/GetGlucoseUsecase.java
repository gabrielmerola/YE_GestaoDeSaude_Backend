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
        return ResponseEntity.status(200).body(glucoseList);
    }
}
