package com.maua.yegestaodesaude.modules.Medicine.get_medicine_by_id.app;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.entities.Medicine;
import com.maua.yegestaodesaude.shared.domain.repositories.MedicineRepository;

@Service
public class GetMedicineByIdUsecase {

    @Autowired
    private MedicineRepository medicineRepository;

    public ResponseEntity<Object> execute(Long medicineId) {
        Optional<Medicine> medicineOptional = medicineRepository.findById(medicineId);

        if (medicineOptional.isPresent()) {
            Medicine medicine = medicineOptional.get();
            GetMedicineByIdViewmodel viewModel = GetMedicineByIdViewmodel.builder()
                    .name(medicine.getName())
                    .time(medicine.getTime())
                    .period(medicine.getPeriod())
                    .quantity(medicine.getQuantity())
                    .dosage(medicine.getDosage())
                    .build();
            return ResponseEntity.status(200).body(viewModel);
        } else {
            return ResponseEntity.status(204).body("{\"message\": \"Medicamento não encontrado\"}");
        }

    }
}