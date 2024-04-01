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
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/consultation")
@Tag(name = "Consultation", description = "Consultation management")
public class CreateConsultationController {

    @Autowired
    private CreateConsultationUsecase createConsultationUsecase;

    @Autowired
    private AutenticationService autenticationService;

    @PostMapping
    @Operation(description = "Criar consulta")
    public CreateConsultationViewmodel createConsultation(@RequestBody ConsultationDTO consultationDTO,
            HttpServletRequest request) {
        try {
            System.out.println("Recebida solicitação para criar consulta.");

            // Extrair token JWT
            String token = extractTokenFromRequest(request);
            System.out.println("Token JWT extraído da solicitação.");

            // Extrair ID do cliente do token JWT
            Long clientId = autenticationService.getClientId(token);
            System.out.println("ID do cliente extraído do token JWT: " + clientId);

            // Executar caso de uso para criar consulta
            this.createConsultationUsecase.execute(consultationDTO, clientId);
            System.out.println("Consulta criada com sucesso.");

            return new CreateConsultationViewmodel("Consulta criada com sucesso");
        } catch (Exception e) {
            System.err.println("Erro ao processar solicitação para criar consulta: " + e.getMessage());
            return new CreateConsultationViewmodel("Erro ao processar solicitação para criar consulta.");
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
