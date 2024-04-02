package com.maua.yegestaodesaude.modules.client.auth_client.app;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maua.yegestaodesaude.shared.domain.dtos.AuthDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/auth")
@Tag(name = "Client")
public class AuthClientController {

    @Autowired
    private AuthClientUsecase authClientUsecase;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Autenticação de cliente")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Cliente autenticado com sucesso",
            content = { @io.swagger.v3.oas.annotations.media.Content(
                mediaType = "application/json",
                schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"token\": \"token\"}")
            )}
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Erro na autenticação do cliente",
            content = { @io.swagger.v3.oas.annotations.media.Content(
                mediaType = "application/json",
                schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"error\": \"Erro na autenticação do cliente\"}")
            )}
        )
    })
    public AuthClientViewmodel authClient(@RequestBody AuthDto authdto) {
        String token = this.authClientUsecase.execute(authdto);
        return new AuthClientViewmodel(token);
    }
}
