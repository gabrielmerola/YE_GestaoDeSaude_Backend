package com.maua.yegestaodesaude.modules.imc.create_imc.app;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.dtos.ImcDTO;

@Service
public class CreateImcUsecase {
    
    public ResponseEntity<Object> execute(ImcDTO imcDTO, Long clientId) {
        return ResponseEntity.ok("oi");
    }
}
