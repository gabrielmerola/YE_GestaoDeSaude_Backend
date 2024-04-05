package com.maua.yegestaodesaude.modules.bloodPressure.get_blood_pressure_by_id.app;

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
@RequestMapping("/blood-pressure")
@Tag(name = "Blood Pressure")
public class GetBloodPressureByIdController {

    @Autowired
    private GetBloodPressureByIdUsecase getBloodPressureByIdUsecase;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Pressão Sanguínea por ID")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pressão Sanguínea encontrada", content = {
                    @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"id\": 1, \"name\": \"Pressão Sanguínea 1\", \"date\": \"2021-10-10\", \"expertise\": \"Cardiologista\", \"dateReturn\": \"2021-10-20\", \"description\": \"Descrição da Pressão Sanguínea\"}"))
            }),
            @ApiResponse(responseCode = "204", description = "Pressão Sanguínea não encontrada", content = {
                    @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Pressão Sanguínea não encontrada\"}"))
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao buscar Pressão Sanguínea", content = {
                    @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Erro ao buscar Pressão Sanguínea.\"}"))
            }),
            @ApiResponse(responseCode = "403", description = "Acesso negado", content = {
                    @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Acesso negado.\"}"))
            })
    })
    public ResponseEntity<Object> execute(@PathVariable Long id) {
        return getBloodPressureByIdUsecase.execute(id);
    }
}
