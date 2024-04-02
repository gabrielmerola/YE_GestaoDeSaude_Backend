package com.maua.yegestaodesaude.modules.glucose.get_glucose.app;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetGlucoseUsecase {
    
    public ResponseEntity<Object> execute() {
        return ResponseEntity.status(200).body("teste");
    }
}
