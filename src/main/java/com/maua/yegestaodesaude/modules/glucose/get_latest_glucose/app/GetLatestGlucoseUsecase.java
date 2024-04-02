package com.maua.yegestaodesaude.modules.glucose.get_latest_glucose.app;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetLatestGlucoseUsecase {
    
    public ResponseEntity<Object> execute(){
        return ResponseEntity.status(200).body("ok");
    }
}
