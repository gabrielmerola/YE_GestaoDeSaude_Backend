package com.maua.yegestaodesaude.modules.consultation.delete_consultation.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultation")
public class DeleteConsultationController {

    @Autowired
    private DeleteConsultationUsecase deleteConsultationUsecase;

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteConsultationById(@PathVariable("id") Long id) {
        var result = deleteConsultationUsecase.execute(id);
        return result;
    }
}
