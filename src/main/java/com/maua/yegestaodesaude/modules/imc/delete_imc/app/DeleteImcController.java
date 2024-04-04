package com.maua.yegestaodesaude.modules.imc.delete_imc.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/imc")
@Tag(name = "IMC")
public class DeleteImcController {
    
    @Autowired
    private DeleteImcUsecase deleteImcUsecase;

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar imc")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "IMC deletado com sucesso!",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"IMC deletado com sucesso!\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Erro ao deletar imc",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Erro ao deletar imc!\"}")
                )
            }
        )
    })
    public ResponseEntity<Object> deleteImc(@PathVariable("id") Long id) {
        try {
            var result = deleteImcUsecase.execute(id);
            return result;
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new DeleteImcViewmodel(e.getMessage()));
        }
    }
}
