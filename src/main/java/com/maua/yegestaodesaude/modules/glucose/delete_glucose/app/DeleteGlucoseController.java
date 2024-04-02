package com.maua.yegestaodesaude.modules.glucose.delete_glucose.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/glucose")
@Tag(name = "Glucose")
public class DeleteGlucoseController {

    @Autowired
    private DeleteGlucoseUsecase deleteGlucoseUsecase;

    @DeleteMapping
    @Operation(summary = "Deletar glicose")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Glicose deletada",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Glicose deletada\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "204",
            description = "Não encontrado",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Não encontrado\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Erro ao deletar glicose",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Erro ao deletar glicose\"}")
                )
            }
        )
    })
    public ResponseEntity<Object> deleteGlucose() {
        try {
            var result = deleteGlucoseUsecase.execute();
            return result;
        } catch (Exception e) {
            return ResponseEntity.status(400).body("{\"message\": \"" + e.getMessage() + "\"}");
        }
    }
    
}
