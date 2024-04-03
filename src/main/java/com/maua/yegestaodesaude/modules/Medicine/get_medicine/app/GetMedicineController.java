package com.maua.yegestaodesaude.modules.Medicine.get_medicine.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maua.yegestaodesaude.shared.services.AutenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/medicine")
@Tag(name = "Medicine")
public class GetMedicineController {
    
    @Autowired
    private GetMedicineUsecase getMedicineUsecase;

    @Autowired
    private AutenticationService autenticationService;

    @GetMapping
    @Operation(summary = "Pegar todos os medicamentos do cliente")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Medicamentos encontrados com sucesso",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "[{\"id\": 1, \"name\": \"Dipirona\", \"time\": \"08:00\", \"period\": \"Manhã\", \"quantity\": 1, \"dosage\": \"1 comprimido\"}]")
                )
            }
        ),
        @ApiResponse(
            responseCode = "204", 
            description = "Não encontrado",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Não encontrado\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Erro ao obter medicamentos",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Erro ao obter medicamentos.\"}")
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
    public ResponseEntity<Object> getMedicine(HttpServletRequest request) {
        try {
            String token = extractTokenFromRequest(request);
            Long clientId = autenticationService.getClientId(token);

            var result = getMedicineUsecase.execute(clientId);
            return result;
        } catch (Exception e) {
            return ResponseEntity.status(400).body("{\"message\": \"Erro ao obter consultas\"}");
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
