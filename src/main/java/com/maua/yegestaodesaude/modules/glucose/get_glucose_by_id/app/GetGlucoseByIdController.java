package com.maua.yegestaodesaude.modules.glucose.get_glucose_by_id.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/glucose")
@Tag(name = "Glucose")
public class GetGlucoseByIdController {
    
    @Autowired
    private GetGlucoseByIdUsecase getGlucoseByIdUsecase;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar glicemia por ID")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "glicemia encontrada",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"id\": 1, \"date\": \"2021-10-10\", \"measure\": \"jejum\", \"level\": 100}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "204",
            description = "glicemia não encontrada",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"glicemia não encontrada\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Erro ao buscar glicemia",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Erro ao buscar glicemia.\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Acesso negado",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Acesso negado.\"}")
                )
            }
        )
    })
    public ResponseEntity<Object> getGlucoseById(@PathVariable("id") Long id) {
        try {
            var result = getGlucoseByIdUsecase.execute(id);
            return result;
        } catch (Exception e) {
            return ResponseEntity.status(400).body("{\"message\": \"Erro ao buscar glicemia\"}");
        }
    }
}
