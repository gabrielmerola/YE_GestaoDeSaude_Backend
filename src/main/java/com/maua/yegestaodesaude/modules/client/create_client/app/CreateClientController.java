package com.maua.yegestaodesaude.modules.client.create_client.app;

import com.maua.yegestaodesaude.shared.domain.dtos.ClientDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Operation(summary = "Criar um novo Cliente")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Cliente criado com sucesso",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\n  \"message\": \"Cliente criado com sucesso\"\n}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Cliente já existe na base de dados",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\n  \"message\": \"Cliente já existe na base de dados\"\n}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "422", 
            description = "Validação dos dados do cliente falhou",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\n  \"message\": \"Validação dos dados do cliente falhou\"\n}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Erro ao criar cliente",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\n  \"message\": \"Erro ao criar cliente\"\n}")
                )
            }
        )
    })
    public ResponseEntity<Object> createClient(@RequestBody ClientDto clientDto){
        try{
            var result = this.createClientUsecase.execute(clientDto);
            return result;
        } catch (Exception exception) {
            // Lidar com outras exceções que possam ocorrer durante a criação do cliente
            return ResponseEntity.status(400).body(new CreateClientViewmodel(exception.getMessage()));
        }
    }
}
