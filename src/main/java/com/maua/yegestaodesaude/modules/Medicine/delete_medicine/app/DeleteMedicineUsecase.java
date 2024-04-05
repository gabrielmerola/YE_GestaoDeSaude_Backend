package com.maua.yegestaodesaude.modules.Medicine.delete_medicine.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.repositories.MedicineRepository;

@Service
public class DeleteMedicineUsecase {

    @Autowired
    private MedicineRepository MedicineRepository;

    public ResponseEntity<Object> execute(Long medicineId) {
        try {
            this.MedicineRepository.deleteById(medicineId);
            return ResponseEntity.status(200).body(DeleteMedicineViewmodel.builder().message("Medicamento excluído com sucesso").build());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(DeleteMedicineViewmodel.builder().message("Erro ao excluir medicamento").build());
        }
    }
}
