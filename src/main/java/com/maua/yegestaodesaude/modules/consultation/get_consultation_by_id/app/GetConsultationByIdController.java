package com.maua.yegestaodesaude.modules.consultation.get_consultation_by_id.app;

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
@RequestMapping("/consultation")
@Tag(name = "Consultation")
public class GetConsultationByIdController {

    @Autowired
    private GetConsultationByIdUsecase getConsultationByIdUsecase;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar consulta por ID")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Consulta encontrada",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"id\": 1, \"name\": \"Consulta 1\", \"date\": \"2021-10-10\", \"expertise\": \"Cardiologista\", \"dateReturn\": \"2021-10-20\", \"description\": \"Descrição da consulta\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "204",
            description = "Consulta não encontrada",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Consulta não encontrada\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Erro ao buscar consulta",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Erro ao buscar consulta.\"}")
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
    public ResponseEntity<Object> getConsultationById(@PathVariable("id") Long id) {
        var result = getConsultationByIdUsecase.execute(id);
        return result;
    }
}
