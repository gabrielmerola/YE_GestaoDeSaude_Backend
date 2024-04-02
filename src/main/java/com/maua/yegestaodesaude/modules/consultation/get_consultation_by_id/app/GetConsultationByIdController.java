package com.maua.yegestaodesaude.modules.consultation.get_consultation_by_id.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultation")
public class GetConsultationByIdController {

    @Autowired
    private GetConsultationByIdUsecase getConsultationByIdUsecase;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getConsultationById(@PathVariable("id") Long id) {
        var result = getConsultationByIdUsecase.execute(id);
        return result;
    }
}
