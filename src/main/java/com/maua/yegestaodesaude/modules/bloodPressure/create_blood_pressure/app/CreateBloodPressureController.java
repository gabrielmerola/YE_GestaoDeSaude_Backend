package com.maua.yegestaodesaude.modules.bloodPressure.create_blood_pressure.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maua.yegestaodesaude.shared.domain.dtos.BloodPressureDTO;
import com.maua.yegestaodesaude.shared.services.AutenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/blood-pressure")
@Tag(name = "Blood Pressure")
public class CreateBloodPressureController {
    
    @Autowired
    private CreateBloodPressureUsecase createBloodPressureUsecase;

    @Autowired
    private AutenticationService autenticationService;

    @PostMapping
    @Operation(description = "Criar pressão arterial")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pressão arterial criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro ao criar pressão arterial")
    })
    public ResponseEntity<Object> createBloodPressure(@RequestBody BloodPressureDTO bloodPressureDTO, HttpServletRequest request){
        try {
            String token = extractTokenFromRequest(request);
            Long clientId = autenticationService.getClientId(token);
            var result = createBloodPressureUsecase.execute(bloodPressureDTO, clientId);
            return result;
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
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
