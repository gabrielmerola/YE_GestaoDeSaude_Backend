package com.maua.yegestaodesaude.modules.imc.create_imc.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maua.yegestaodesaude.shared.domain.dtos.ImcDTO;
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
public class CreateImcController {

    @Autowired
    private CreateImcUsecase createImcUsecase;
    
    @Autowired
    private AutenticationService autenticationService;

    @PostMapping
    @Operation(summary = "Criar IMC")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "IMC criado com sucesso",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"IMC criado com sucesso\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Erro ao criar IMC",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Erro ao criar IMC.\"}")
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
    public ResponseEntity<Object> createImc(@RequestBody ImcDTO imcDTO, HttpServletRequest request) {
        try {
            String token = extractTokenFromRequest(request);
            Long clientId = autenticationService.getClientId(token);

            var result = createImcUsecase.execute(imcDTO, clientId);
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
