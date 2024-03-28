package com.maua.yegestaodesaude.modules.get_client.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maua.yegestaodesaude.shared.services.AutenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/client")
public class GetClientController {

    @Autowired
    private GetClientUsecase getClientUsecase;

    @Autowired
    private AutenticationService autenticationService;

    @Operation(description = "Buscar um cliente pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "400", description = "Cliente não encontrado")
    })
    @GetMapping("/")
    public ResponseEntity<Object> getClient(HttpServletRequest request) {
        try {
            System.out.println("Entrou no método getClient");
            String token = extractTokenFromRequest(request);
            Long clientId = autenticationService.getClientId(token);
            var client = this.getClientUsecase.execute(clientId);
            System.out.println("Cliente encontrado: " + client);
            return ResponseEntity.ok().body(client);
        } catch (Exception exception) {
            System.out.println("Erro ao buscar cliente");
            System.out.println("Exception: " + exception.getMessage());
            return ResponseEntity.badRequest().body(exception.getMessage());
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
