package com.maua.yegestaodesaude.modules.get_client.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/client")
public class GetClientController {

    @Autowired
    private GetClientUsecase getClientUsecase;

    @Operation(description = "Buscar um cliente pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "400", description = "Cliente não encontrado")
    })
    public ResponseEntity<Object> getClient(Long idClient) {
        try {
            var client = this.getClientUsecase.execute(idClient);
            return ResponseEntity.ok().body(client);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
