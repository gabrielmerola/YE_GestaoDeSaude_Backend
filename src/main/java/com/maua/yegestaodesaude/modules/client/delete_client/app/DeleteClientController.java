package com.maua.yegestaodesaude.modules.client.delete_client.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maua.yegestaodesaude.shared.services.AutenticationService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/client")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
        @ApiResponse(responseCode = "400", description = "Erro ao deletar cliente")
})
public class DeleteClientController {

    @Autowired
    private DeleteClientUsecase deleteClientUsecase;
    
    @Autowired
    private AutenticationService authenticationService;

    @DeleteMapping("/")
    public ResponseEntity<String> deleteClient(HttpServletRequest request) {
        try {
            String token = extractTokenFromRequest(request);
            Long clientId = authenticationService.getClientId(token);
            deleteClientUsecase.execute(clientId);
            return ResponseEntity.ok("Cliente deletado com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar cliente");
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
