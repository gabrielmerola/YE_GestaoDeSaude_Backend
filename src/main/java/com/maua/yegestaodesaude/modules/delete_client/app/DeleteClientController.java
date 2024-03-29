package com.maua.yegestaodesaude.modules.delete_client.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
        @ApiResponse(responseCode = "400", description = "Erro ao deletar cliente")
})
public class DeleteClientController {

    private final DeleteClientUsecase deleteClientUsecase;

    @Autowired
    public DeleteClientController(DeleteClientUsecase deleteClientUsecase) {
        this.deleteClientUsecase = deleteClientUsecase;
    }

    @DeleteMapping("/client/{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable Long clientId) {
        try {
            deleteClientUsecase.execute(clientId);
            return ResponseEntity.ok("Cliente deletado com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar cliente");
        }
    }
}
