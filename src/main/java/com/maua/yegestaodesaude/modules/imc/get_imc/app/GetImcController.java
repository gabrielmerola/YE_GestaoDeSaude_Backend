package com.maua.yegestaodesaude.modules.imc.get_imc.app;

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
@RequestMapping("/imc")
@Tag(name = "IMC")
public class GetImcController {

    @Autowired
    private GetImcUsecase getImcUsecase;

    @Autowired
    private AutenticationService autenticationService;

        @GetMapping("/imc")
        @Operation(summary = "Obter IMC")
        @SecurityRequirement(name = "bearerAuth")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "IMC encontrado", content = {
                @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"imc\": 25.5}"))
            }),
            @ApiResponse(responseCode = "204", description = "Não encontrado", content = {
                @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Não encontrado\"}"))
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao obter IMC", content = {
                @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Erro ao obter IMC\"}"))
            }),
            @ApiResponse(responseCode = "403", description = "Acesso negado", content = {
                @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Acesso negado\"}"))
            })
        })
    public ResponseEntity<Object> getImc(HttpServletRequest request) {
        try {
            String token = extractTokenFromRequest(request);
            Long clientId = autenticationService.getClientId(token);

            var result = getImcUsecase.execute(clientId);
            return result;
        } catch (Exception e) {
            return ResponseEntity.status(400).body("{\"message\": \"Erro ao obter imc\"}");
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
