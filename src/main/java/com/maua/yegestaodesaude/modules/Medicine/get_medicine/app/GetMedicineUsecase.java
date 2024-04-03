package com.maua.yegestaodesaude.modules.Medicine.get_medicine.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.entities.Medicine;
import com.maua.yegestaodesaude.shared.domain.repositories.MedicineRepository;

@Service
public class GetMedicineUsecase {
    
    @Autowired
    private MedicineRepository medicineRepository;

    public ResponseEntity<Object> execute(Long clientId) {
        List<Medicine> medicine = medicineRepository.findAllByClientId(clientId);

        if(medicine.isEmpty()) {
            return ResponseEntity.status(204).body("{\"message\": \"Não encontrado\"}");
        }

        List<GetMedicineViewmodel> medicineList = new ArrayList<>();

        for(Medicine m : medicine){
            medicineList.add(GetMedicineViewmodel.builder()
                    .id(m.getId())
                    .name(m.getName())
                    .time(m.getTime())
                    .period(m.getPeriod())
                    .quantity(m.getQuantity())
                    .dosage(m.getDosage())
                    .build());
        }
        return ResponseEntity.status(200).body(medicineList);
    }
}
