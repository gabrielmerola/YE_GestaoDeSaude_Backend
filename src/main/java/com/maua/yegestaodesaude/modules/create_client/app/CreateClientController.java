package com.maua.yegestaodesaude.modules.create_client.app;

import com.maua.yegestaodesaude.shared.domain.dtos.ClientDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@Tag(name = "Client")
public class CreateClientController {

    @Autowired
    private CreateClientUsecase createClientUsecase;

    @PostMapping
    @Operation(description = "Criar um novo Cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente criado com sucesso"),
        @ApiResponse(responseCode = "409", description = "Cliente já existe na base de dados"),
        @ApiResponse(responseCode = "422", description = "Validação dos dados do cliente falhou"),
        @ApiResponse(responseCode = "400", description = "Erro ao criar cliente")
    })
    public ResponseEntity<Object> createClient(@RequestBody ClientDto clientDto){
        try{
            var result = this.createClientUsecase.execute(clientDto);
            return result;
        } catch (Exception exception) {
            // Lidar com outras exceções que possam ocorrer durante a criação do cliente
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
