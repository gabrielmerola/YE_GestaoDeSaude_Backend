package com.maua.yegestaodesaude.modules.client.update_password.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maua.yegestaodesaude.shared.domain.dtos.UpdateClientDTO;
import com.maua.yegestaodesaude.shared.helpers.errors.EntityError;
import com.maua.yegestaodesaude.shared.helpers.errors.MissingParameters;
import com.maua.yegestaodesaude.shared.helpers.errors.WrongTypeParameters;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping("/client")
@Tag(name = "Client")
public class UpdatePasswordController {

    @Autowired
    private UpdatePasswordUsecase updatePasswordUsecase;

    @PutMapping("/password")
    @Operation(summary = "Atualizar senha do cliente")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Senha atualizada com sucesso",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Senha atualizada com sucesso\"}")
                )
            }    
        ),
        @ApiResponse(
            responseCode = "204",
            description = "Cliente não encontrado",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Cliente não encontrado\"}")
                )
            }  
        ),    
        @ApiResponse(
            responseCode = "422",
            description = "Validação dos dados do cliente falhou",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Parâmetros faltando\"}")
                )
            }  
        ),    
        @ApiResponse(
            responseCode = "400",
            description = "Erro ao atualizar senha",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Erro ao atualizar senha\"}")
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
        ),
    })
    public ResponseEntity<Object> updatePassword(@RequestBody UpdateClientDTO updateClientDTO) {
        try {
            String email = updateClientDTO.getEmail();
            String newPassword = updateClientDTO.getNewPassword();

            if (email == null) {
                return ResponseEntity.status(422).body(new MissingParameters("Email"));
            }

            if (!email.getClass().getSimpleName().equals("String")) {
                return ResponseEntity.status(422).body(new WrongTypeParameters("email", "string", email.getClass().getSimpleName()));
            }

            if (newPassword == null) {
                return ResponseEntity.status(422).body(new MissingParameters("newPassword"));
            }

            if (!newPassword.getClass().getSimpleName().equals("String")) {
                return ResponseEntity.status(422).body(new WrongTypeParameters("newPassword", "string", newPassword.getClass().getSimpleName()));
            }

            this.updatePasswordUsecase.execute(email, newPassword);
            return ResponseEntity.status(200).body(new UpdatePasswordViewmodel("Senha atualizada com sucesso"));
        } catch (EntityError e) {
            // Captura a exceção EntityError e retorna uma resposta personalizada
            return ResponseEntity.status(400).body(new UpdatePasswordViewmodel(e.getMessage()));
        }
    }
}
