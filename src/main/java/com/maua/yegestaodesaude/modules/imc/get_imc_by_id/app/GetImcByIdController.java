package com.maua.yegestaodesaude.modules.imc.get_imc_by_id.app;

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
@RequestMapping("/imc")
@Tag(name = "IMC")
public class GetImcByIdController {
    
    @Autowired
    private GetImcByIdUsecase getImcByIdUsecase;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar IMC por ID")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "IMC encontrada",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"id\": 1, \"weight\": 70, \"height\": 1.70, \"imc\": 24.22, \"level\": \"Normal\", \"date\": \"2021-10-10\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "204",
            description = "IMC não encontrada",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"IMC não encontrada\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Erro ao buscar IMC",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Erro ao buscar IMC.\"}")
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
    public ResponseEntity<Object> getImcById(@PathVariable("id") Long id) {
        try {
            var result = getImcByIdUsecase.execute(id);
            return result;
        } catch (Exception e) {
            return ResponseEntity.status(400).body("{\"message\": \"Erro ao buscar IMC.\"}");
        }
    }

}
