package com.maua.yegestaodesaude.modules.glucose.get_latest_glucose.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/glucose")
@Tag(name = "Glucose")
public class GetLatestGlucoseController {
    
    @Autowired
    private GetLatestGlucoseUsecase getLatestGlucoseUsecase;

    @GetMapping("/latest")
    @Operation(summary = "Obter última glicose")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Glicose encontrada",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"id\": 1, \"date\": \"2021-10-10\", \"measure\": 100, \"level\": \"normal\"}")
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
            description = "Erro ao obter glicose",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Erro ao obter glicose\"}")
                )
            }
        )
    })
    public ResponseEntity<Object> getLatestGucose(){
        try {
            var result = getLatestGlucoseUsecase.execute();
            return result;
        } catch (Exception e) {
            return ResponseEntity.status(400).body("{\"message\": \""+ e.getMessage() +"\"}");
        }
    }
}
