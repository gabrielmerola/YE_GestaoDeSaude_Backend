package com.maua.yegestaodesaude.modules.glucose.get_latest_glucose.app;

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
@RequestMapping("/glucose")
@Tag(name = "Glucose")
public class GetLatestGlucoseController {
    
    @Autowired
    private GetLatestGlucoseUsecase getLatestGlucoseUsecase;

    @Autowired
    private AutenticationService authenticationService;

    @GetMapping("/latest")
    @Operation(summary = "Obter última glicose")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Glicose encontrada",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"id\": 1, \"date\": \"2021-10-10\", \"measure\": 100, \"level\": \"normal\"}")
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
            description = "Erro ao obter glicose",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Erro ao obter glicose\"}")
                )
            }
        )
    })
    public ResponseEntity<Object> getLatestGucose(HttpServletRequest request){
        try {
            String token = extractTokenFromRequest(request);
            Long clientId = authenticationService.getClientId(token);
            var result = getLatestGlucoseUsecase.execute(clientId);
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
