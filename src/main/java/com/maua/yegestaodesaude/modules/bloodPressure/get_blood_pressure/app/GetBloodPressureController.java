package com.maua.yegestaodesaude.modules.bloodPressure.get_blood_pressure.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maua.yegestaodesaude.shared.services.AutenticationService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/blood-pressure")
@Tag(name = "Blood Pressure")
public class GetBloodPressureController {
    
    @Autowired
    private GetBloodPressureUsecase getBloodPressureUsecase;
    
    @Autowired
    private AutenticationService autenticationService;

    @GetMapping
    public ResponseEntity<Object> getBloodPressure(HttpServletRequest request){
        try {
            String token = extractTokenFromRequest(request);
            Long clientId = autenticationService.getClientId(token);
    
            var result = getBloodPressureUsecase.execute(clientId);
            return result;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        } else {
            throw new RuntimeException("Token not found in request headers");
        }
    }
}
