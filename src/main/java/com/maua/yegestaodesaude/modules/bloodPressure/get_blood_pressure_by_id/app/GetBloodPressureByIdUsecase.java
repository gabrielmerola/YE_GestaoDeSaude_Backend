package com.maua.yegestaodesaude.modules.bloodPressure.get_blood_pressure_by_id.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.entities.BloodPressure;
import com.maua.yegestaodesaude.shared.domain.repositories.BloodPressureRepository;
import com.maua.yegestaodesaude.shared.utils.DateUtils;

@Service
public class GetBloodPressureByIdUsecase {

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private BloodPressureRepository bloodPressureRepository;

    public ResponseEntity<Object> execute(Long bloodPressureId) {
        BloodPressure bloodPressure = bloodPressureRepository.findById(bloodPressureId).orElse(null);

        if (bloodPressure != null) {
            // BloodPressure measureLevel = new BloodPressure();
            GetBloodPressureByIdViewmodel viewModel = GetBloodPressureByIdViewmodel.builder()
                    .measure(bloodPressure.getMeasure())
                    .date(dateUtils.RevertDate(bloodPressure.getDate()))
                    .level(bloodPressure.levelPressure(bloodPressure.getMeasure()))
                    .build();
            return ResponseEntity.status(200).body(viewModel);
        } else {
            return ResponseEntity.status(204).body("{\"message\": \"Pressão arterial não encontrada\"}");
        }
    }
}