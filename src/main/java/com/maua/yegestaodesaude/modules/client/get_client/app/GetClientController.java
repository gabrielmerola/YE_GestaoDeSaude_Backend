package com.maua.yegestaodesaude.modules.client.get_client.app;

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
@RequestMapping("/client")
@Tag(name = "Client")
public class GetClientController {

    @Autowired
    private GetClientUsecase getClientUsecase;

    @Autowired
    private AutenticationService autenticationService;

    @Operation(summary = "Buscar um cliente pelo id")
    @ApiResponses(value = {
            @ApiResponse(
                responseCode = "200",
                description = "Cliente encontrado",
                content = {
                    @io.swagger.v3.oas.annotations.media.Content(
                        mediaType = "application/json",
                        schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = GetClientViewmodel.class)
                    )
                }
            ),
            @ApiResponse(
                responseCode = "204",
                description = "Cliente não encontrado",
                content = {
                    @io.swagger.v3.oas.annotations.media.Content(
                        mediaType = "application/json",
                        schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\n  \"message\": \"Cliente não encontrado\"\n}")
                    )
                }
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Erro ao buscar cliente",
                content = {
                    @io.swagger.v3.oas.annotations.media.Content(
                        mediaType = "application/json",
                        schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\n  \"message\": \"Erro ao buscar cliente\"\n}")
                    )
                }
            )
    })
    @GetMapping
    public ResponseEntity<Object> getClient(HttpServletRequest request) {
        try {
            String token = extractTokenFromRequest(request);
            Long clientId = autenticationService.getClientId(token);
            var client = this.getClientUsecase.execute(clientId);
            return ResponseEntity.status(200).body(client);
        } catch (Exception exception) {
            String errorMessage = exception.getMessage();
            return ResponseEntity.status(400).body("{\"message\": \"" + errorMessage + "\"}");
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
