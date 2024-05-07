package com.maua.yegestaodesaude.modules.imc.get_imc_by_date.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class GetImcByDateController {
    
    @Autowired
    private GetImcByDateUsecase getImcByDateUsecase;

    @Autowired
    private AutenticationService autenticationService;

    @GetMapping("/date/{date}")
    @Operation(summary = "Obter IMC por data")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "IMC obtido com sucesso",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"id\": 1, \"weight\": 70.0, \"height\": 1.75, \"imc\": 22.86, \"level\": \"Normal\", \"date\": \"2021-10-10\"}")
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
            description = "Acesso negado",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Acesso negado\"}")
                )
            }
        )
    })
    public ResponseEntity<Object> getImcByDate(@PathVariable("date") String date, HttpServletRequest request){
        try {
            String token = extractTokenFromRequest(request);
            Long clientId = autenticationService.getClientId(token);

            var result = getImcByDateUsecase.execute(clientId, date);
            return result;
        } catch (Exception e) {
            return ResponseEntity.status(400).body("{\"message\": \"Erro ao obter IMC\"}");
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
