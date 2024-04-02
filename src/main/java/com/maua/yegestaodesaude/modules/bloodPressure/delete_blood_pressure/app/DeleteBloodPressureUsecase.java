package com.maua.yegestaodesaude.modules.bloodPressure.delete_blood_pressure.app;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.entities.BloodPressure;
import com.maua.yegestaodesaude.shared.domain.repositories.BloodPressureRepository;

@Service
public class DeleteBloodPressureUsecase {
    
    @Autowired
    private BloodPressureRepository bloodPressureRepository;

    public ResponseEntity<Object> execute(Long bloodPressureId){
        Optional<BloodPressure> bloodPressureOptional = bloodPressureRepository.findById(bloodPressureId);

        if(!bloodPressureOptional.isPresent()){
            return ResponseEntity.status(204).body(new DeleteBloodPressureViewmodel("Registro de pressão arterial não encontrado!"));
        }

        BloodPressure bloodPressure = bloodPressureOptional.get();
        bloodPressureRepository.delete(bloodPressure);
        return ResponseEntity.status(200).body(new DeleteBloodPressureViewmodel("Registro de pressão arterial deletado com sucesso!"));
    }
}
