package com.maua.yegestaodesaude.modules.glucose.get_glucose.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/glucose")
@Tag(name = "Glucose")
public class GetGlucoseController {
    
    @Autowired
    private GetGlucoseUsecase getGlucoseUsecase;

    @GetMapping
    @Operation(summary = "Obter glicoses")
    @ApiResponses(value = {
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
    public ResponseEntity<Object> getGlucose() {
        var result = getGlucoseUsecase.execute(); 
        return result;
    }
}
