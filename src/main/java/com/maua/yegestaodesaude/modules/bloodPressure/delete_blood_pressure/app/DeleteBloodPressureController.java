package com.maua.yegestaodesaude.modules.bloodPressure.delete_blood_pressure.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/blood-pressure")
@Tag(name = "Blood Pressure")
public class DeleteBloodPressureController {

    @Autowired
    private DeleteBloodPressureUsecase deleteBloodPressureUsecase;

    @DeleteMapping
    public ResponseEntity<Object> deleteBloodPressure(@RequestParam Long id){
        try {
            var result = deleteBloodPressureUsecase.execute(id);
            return result;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar o registro da pressão arterial.");
        }
    }
}
