package com.maua.yegestaodesaude.modules.consultation.delete_consultation.app;

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
@RequestMapping("/consultation")
@Tag(name = "Consultation")
public class DeleteConsultationController {

    @Autowired
    private DeleteConsultationUsecase deleteConsultationUsecase;

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar consulta por ID")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta deletada com sucesso", content = {
                    @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = DeleteConsultationViewmodel.class, example = "{\"message\": \"Consulta deletada com sucesso\"}"))
            }),
            @ApiResponse(responseCode = "204", description = "Consulta não encontrada", content = {
                    @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = DeleteConsultationViewmodel.class, example = "{\"message\": \"Consulta não encontrada\"}"))
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao deletar consulta", content = {
                    @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = DeleteConsultationViewmodel.class, example = "{\"message\": \"Erro ao deletar consulta\"}"))
            })
    })
    public ResponseEntity<Object> deleteConsultationById(@PathVariable("id") Long id) {
        var result = deleteConsultationUsecase.execute(id);
        return result;
    }
}
