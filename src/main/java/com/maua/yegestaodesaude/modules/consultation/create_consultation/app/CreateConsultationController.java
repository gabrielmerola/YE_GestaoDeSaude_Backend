package com.maua.yegestaodesaude.modules.consultation.create_consultation.app;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maua.yegestaodesaude.shared.domain.dtos.ConsultationDTO;
import com.maua.yegestaodesaude.shared.services.AutenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/consultation")
@Tag(name = "Consultation")
public class CreateConsultationController {

    @Autowired
    private CreateConsultationUsecase createConsultationUsecase;

    @Autowired
    private AutenticationService autenticationService;

    @PostMapping
    @Operation(summary = "Criar consulta")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "Consulta criada com sucesso", 
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CreateConsultationViewmodel.class)
                )
            }
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Erro ao processar solicitação para criar consulta",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CreateConsultationViewmodel.class)
                )
            }
        ),
        @ApiResponse(
            responseCode = "403", 
            description = "Acesso negado",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CreateConsultationViewmodel.class)
                )
            }
        ),
        @ApiResponse(
            responseCode = "422", 
            description = "Informações inválidas para criar consulta",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CreateConsultationViewmodel.class)
                )
            }
        )
    })
    public ResponseEntity<Object> createConsultation(@RequestBody ConsultationDTO consultationDTO,
            HttpServletRequest request) {
        try {
            // Extrair token JWT
            String token = extractTokenFromRequest(request);

            // Extrair ID do cliente do token JWT
            Long clientId = autenticationService.getClientId(token);

            // Executar caso de uso para criar consulta
            this.createConsultationUsecase.execute(consultationDTO, clientId);

            return ResponseEntity.status(201).body(new CreateConsultationViewmodel("Consulta criada com sucesso"));
        } catch (Exception e) {
            System.err.println("Erro ao processar solicitação para criar consulta: " + e.getMessage());
            return ResponseEntity.status(400).body(new CreateConsultationViewmodel("Erro ao processar solicitação para criar consulta."));
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
