package com.maua.yegestaodesaude.modules.client.delete_client.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maua.yegestaodesaude.modules.client.get_client.app.GetClientViewmodel;
import com.maua.yegestaodesaude.shared.services.AutenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/client")
@Tag(name = "Client")
public class DeleteClientController {

    @Autowired
    private DeleteClientUsecase deleteClientUsecase;
    
    @Autowired
    private AutenticationService authenticationService;

    @DeleteMapping
    @Operation(summary = "Deletar cliente")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Cliente deletado com sucesso",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\n  \"message\": \"Cliente deletado com sucesso\"\n}")
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
            description = "Erro ao deletar cliente",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\n  \"message\": \"Erro ao deletar cliente\"\n}")
                )
            }
        )
    })
    public ResponseEntity<Object> deleteClient(HttpServletRequest request) {
        try {
            String token = extractTokenFromRequest(request);
            Long clientId = authenticationService.getClientId(token);
            deleteClientUsecase.execute(clientId);
            return ResponseEntity.status(200).body( new DeleteClientViewmodel("Cliente deletado com sucesso"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new DeleteClientViewmodel("Erro ao deletar cliente"));
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
