package com.maua.yegestaodesaude.modules.glucose.get_glucose_by_id.app;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.entities.Glucose;
import com.maua.yegestaodesaude.shared.domain.repositories.GlucoseRepository;
import com.maua.yegestaodesaude.shared.utils.DateUtils;

@Service
public class GetGlucoseByIdUsecase {

    @Autowired
    private DateUtils dateUtils;
    
    @Autowired
    private GlucoseRepository glucoseRepository;

    public ResponseEntity<Object> execute(Long id) {
        Optional<Glucose> glucoseOptional = glucoseRepository.findById(id);

        if(glucoseOptional.isPresent()){
            Glucose glucose = glucoseOptional.get();
            GetGlucoseByIdViewmodel viewModel = GetGlucoseByIdViewmodel.builder()
                    .id(glucose.getId())
                    .date(dateUtils.RevertDate(glucose.getDate()))
                    .measure(glucose.getMeasure())
                    .level(glucose.getLevel())
                    .build();
            return ResponseEntity.status(200).body(viewModel);
        }else{
            return ResponseEntity.status(204).body("{\"message\": \"Glicemia não encontrada\"}");
        }
    }
}
