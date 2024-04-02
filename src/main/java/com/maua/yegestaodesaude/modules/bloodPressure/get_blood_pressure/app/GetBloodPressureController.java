package com.maua.yegestaodesaude.modules.bloodPressure.get_blood_pressure.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maua.yegestaodesaude.shared.services.AutenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Operation(summary = "Obter pressão arterial")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Pressão arterial obtida com sucesso",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = GetBloodPressureViewmodel.class)
                )
            }
        ),
        @ApiResponse(
            responseCode = "204",
            description = "Pressão arterial não encontrada",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Pressão arterial não encontrada\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Erro ao obter pressão arterial",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Erro ao obter pressão arterial\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "403", 
            description = "Acesso negado",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Acesso negado\"}")
                )
            }
        )
    })
    public ResponseEntity<Object> getBloodPressure(HttpServletRequest request){
        try {
            String token = extractTokenFromRequest(request);
            Long clientId = autenticationService.getClientId(token);
    
            var result = getBloodPressureUsecase.execute(clientId);
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
