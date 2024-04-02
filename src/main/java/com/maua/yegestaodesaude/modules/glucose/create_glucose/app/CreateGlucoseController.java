package com.maua.yegestaodesaude.modules.glucose.create_glucose.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maua.yegestaodesaude.shared.domain.dtos.GlucoseDTO;
import com.maua.yegestaodesaude.shared.services.AutenticationService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/glucose")
@Tag(name = "Glucose")
public class CreateGlucoseController {
    
    @Autowired
    private CreateGlucoseUsecase createGlucoseUsecase;

    @Autowired
    private AutenticationService autenticationService;

    @PostMapping
    @Operation(description = "criar glicose")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "glicose criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro ao criar glicose"),
        @ApiResponse(responseCode = "422", description = "informações não fornecidas corretamente")
    })
    public ResponseEntity<Object> createGlucose(@RequestBody GlucoseDTO glucoseDTO, HttpServletRequest request){
        try {
            String token = extractTokenFromRequest(request);
            Long clientId = autenticationService.getClientId(token);
            if(glucoseDTO.measure() == null || glucoseDTO.date() == null){
                return ResponseEntity.status(422).body("Medida e data são obrigatórios");
            }
            if(glucoseDTO.measure() < 0 && glucoseDTO.measure() > 1000){
                return ResponseEntity.status(422).body("Medida não pode ser negativa");
            }
            var result = createGlucoseUsecase.execute(glucoseDTO, clientId);
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
