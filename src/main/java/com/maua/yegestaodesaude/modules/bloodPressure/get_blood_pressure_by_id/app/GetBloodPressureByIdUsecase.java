package com.maua.yegestaodesaude.modules.bloodPressure.get_blood_pressure_by_id.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.modules.bloodPressure.get_blood_pressure_by_id.GetBloodPressureByIdViewmodel;
import com.maua.yegestaodesaude.shared.domain.entities.BloodPressure;
import com.maua.yegestaodesaude.shared.domain.repositories.BloodPressureRepository;

@Service
public class GetBloodPressureByIdUsecase {

    @Autowired
    private BloodPressureRepository bloodPressureRepository;

    public ResponseEntity<Object> execute(Long bloodPressureId) {
        BloodPressure bloodPressure = bloodPressureRepository.findById(bloodPressureId).orElse(null);

        if (bloodPressure != null) {
            // BloodPressure measureLevel = new BloodPressure();
            GetBloodPressureByIdViewmodel viewModel = GetBloodPressureByIdViewmodel.builder()
                    .measure(bloodPressure.getMeasure())
                    .date(bloodPressure.getDate())
                    .level(bloodPressure.levelPressure(bloodPressure.getMeasure()))
                    .build();
            return ResponseEntity.status(200).body(viewModel);
        } else {
            return ResponseEntity.status(204).body("{\"message\": \"Pressão arterial não encontrada\"}");
        }
    }
}