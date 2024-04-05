package com.maua.yegestaodesaude.modules.Medicine.delete_medicine.app;

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
@RequestMapping("/medicine")
public class DeleteMedicineController {

    @Autowired
    private DeleteMedicineUsecase deleteMedicineUsecase;

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar medicamento por ID")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta deletada com sucesso", content = {
                    @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = DeleteMedicineViewmodel.class, example = "{\"message\": \"Consulta deletada com sucesso\"}"))
            }),
            @ApiResponse(responseCode = "204", description = "Consulta não encontrada", content = {
                    @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = DeleteMedicineViewmodel.class, example = "{\"message\": \"Consulta não encontrada\"}"))
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao deletar consulta", content = {
                    @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = DeleteMedicineViewmodel.class, example = "{\"message\": \"Erro ao deletar consulta\"}"))
            })
    })
    public ResponseEntity<Object> deleteMedicineById(@PathVariable("id") Long id) {
        var result = deleteMedicineUsecase.execute(id);
        return result;
    }
}
