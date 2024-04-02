package com.maua.yegestaodesaude.modules.bloodPressure.delete_blood_pressure.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/blood-pressure")
@Tag(name = "Blood Pressure")
public class DeleteBloodPressureController {

    @Autowired
    private DeleteBloodPressureUsecase deleteBloodPressureUsecase;

    @DeleteMapping
    @Operation(summary = "Deletar pressão arterial")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Pressão arterial deletada com sucesso",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Pressão arterial deletada com sucesso\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "204", 
            description = "Registro de pressão arterial não encontrado",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Registro de pressão arterial não encontrado!\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Erro ao deletar pressão arterial",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json", 
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Erro ao deletar pressão arterial\"}")
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
        )
    })
    public ResponseEntity<Object> deleteBloodPressure(@RequestParam Long id){
        try {
            var result = deleteBloodPressureUsecase.execute(id);
            return result;
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new DeleteBloodPressureViewmodel("Erro ao deletar o registro da pressão arterial."));
        }
    }
}
