package com.maua.yegestaodesaude.modules.Medicine.create_medicine.app;

import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.modules.imc.create_imc.app.CreateImcViewmodel;
import com.maua.yegestaodesaude.shared.domain.dtos.MedicineDTO;
import com.maua.yegestaodesaude.shared.domain.entities.Client;
import com.maua.yegestaodesaude.shared.domain.entities.Medicine;
import com.maua.yegestaodesaude.shared.domain.repositories.ClientRepository;
import com.maua.yegestaodesaude.shared.domain.repositories.MedicineRepository;

@Service
public class CreateMedicineUsecase {
    
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    public ResponseEntity<Object> execute(MedicineDTO medicineDTO, Long clientId){
        try {
            Client client = this.clientRepository.findById(clientId).orElseThrow(() -> {
                throw new RuntimeException("Cliente não encontrado!");
            });

            Medicine medicine = new Medicine();
            medicine.setClient(client);

            Time time = Time.valueOf(medicineDTO.time());
            medicine.setTime(time);
            
            medicine.setName(medicineDTO.name());
            medicine.setPeriod(medicineDTO.period());
            medicine.setQuantity(medicineDTO.quantity());
            medicine.setDosage(medicineDTO.dosage());

            this.medicineRepository.save(medicine);
            if(medicine.getId() == null){
                return ResponseEntity.status(400).body(new CreateMedicineViewmodel("Erro ao salvar medicamento!"));
            }
            return ResponseEntity.status(201).body(new CreateMedicineViewmodel("Medicamento criado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new CreateMedicineViewmodel(e.getMessage()));
        }
    }
}
