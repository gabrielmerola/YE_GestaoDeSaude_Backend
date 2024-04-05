package com.maua.yegestaodesaude.modules.Medicine.get_medicine_by_id.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping; // Add this import statement

@RestController
@RequestMapping("/medicine")
@Tag(name = "Medicine")
public class GetMedicineByIdController {

    @Autowired
    private GetMedicineByIdUsecase getMedicineByIdUsecase;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar consulta por ID")
    @SecurityRequirement(name = "bearerAuth")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medicamento encontrado", content = {
                @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"id\": 1, \"name\": \"Medicamento 1\", \"description\": \"Descrição do medicamento\"}"))
            }),
            @ApiResponse(responseCode = "204", description = "Medicamento não encontrado", content = {
                @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Medicamento não encontrado\"}"))
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao buscar medicamento", content = {
                @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Erro ao buscar medicamento.\"}"))
            }),
            @ApiResponse(responseCode = "403", description = "Acesso negado", content = {
                @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Acesso negado.\"}"))
            })
        })
    public ResponseEntity<Object> getMedicineById(@PathVariable Long id) {
        return getMedicineByIdUsecase.execute(id);
    }
}
