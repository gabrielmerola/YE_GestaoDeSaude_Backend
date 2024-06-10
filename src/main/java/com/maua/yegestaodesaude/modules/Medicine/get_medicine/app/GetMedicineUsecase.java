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

        int n = medicineList.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (medicineList.get(j).getId() > medicineList.get(j + 1).getId()) {
                    GetMedicineViewmodel temp = medicineList.get(j);
                    medicineList.set(j, medicineList.get(j + 1));
                    medicineList.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return ResponseEntity.status(200).body(medicineList);
    }
}
