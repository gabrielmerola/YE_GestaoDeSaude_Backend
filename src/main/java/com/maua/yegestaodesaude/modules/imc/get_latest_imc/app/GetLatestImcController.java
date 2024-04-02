package com.maua.yegestaodesaude.modules.imc.get_latest_imc.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

import com.maua.yegestaodesaude.shared.services.AutenticationService;

@RestController
@RequestMapping("/imc")
@Tag(name = "IMC")
public class GetLatestImcController {

    @Autowired
    private AutenticationService autenticationService;

    @Autowired
    private GetLatestImcUsecase getLatestImcUsecase;
    
    @GetMapping("/latest")
    @Operation(summary = "ultimo imc cadastrado")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Retorna o último IMC cadastrado",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"id\": 1, \"weight\": 100, \"height\": 1.80, \"imc\": 30.86, \"level\": \"Obesidade Grau I\", \"date\": \"2021-10-10\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "204",
            description = "Sem conteúdo",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Sem conteúdo\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Erro ao obter IMC",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Erro ao obter IMC\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Acesso Negado",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Acesso Negado\"}")
                )
            }
        )
    })
    public ResponseEntity<Object> getLatestImc(HttpServletRequest request){
        try {
            String token = extractTokenFromRequest(request);
            Long clientId = autenticationService.getClientId(token);

            var result = getLatestImcUsecase.execute(clientId);
            return result;
        } catch (Exception e) {
            return ResponseEntity.status(400).body("{\"message\": \""+ e.getMessage() +"\"}");
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
