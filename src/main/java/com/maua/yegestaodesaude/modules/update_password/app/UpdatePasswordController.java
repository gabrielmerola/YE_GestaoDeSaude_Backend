package com.maua.yegestaodesaude.modules.update_password.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
@RestController
@RequestMapping("/client")
public class UpdatePasswordController {

    @Autowired
    private UpdatePasswordUsecase updatePasswordUsecase;

    @PostMapping("/update-password")
    @Operation(description = "Atualizar senha do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Senha atualizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "422", description = "Validação dos dados do cliente falhou"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar senha"),
    })
    public UpdatePasswordViewmodel updatePassword(@RequestBody UpdateClientDTO updateClientDTO) {
        try {
            String email = updateClientDTO.getEmail();
            String newPassword = updateClientDTO.getNewPassword();

            if (email == null) {
                throw new MissingParameters("email");
            }

            if (!email.getClass().getSimpleName().equals("String")) {
                throw new WrongTypeParameters("email", "string", email.getClass().getSimpleName());
            }

            if (newPassword == null) {
                throw new MissingParameters("newPassword");
            }

            if (!newPassword.getClass().getSimpleName().equals("String")) {
                throw new WrongTypeParameters("newPassword", "string", newPassword.getClass().getSimpleName());
            }

            this.updatePasswordUsecase.execute(email, newPassword);
            return new UpdatePasswordViewmodel("Senha atualizada com sucesso");
        } catch (EntityError e) {
            // Captura a exceção EntityError e retorna uma resposta personalizada
            return new UpdatePasswordViewmodel(e.getMessage());
        }
    }
}
