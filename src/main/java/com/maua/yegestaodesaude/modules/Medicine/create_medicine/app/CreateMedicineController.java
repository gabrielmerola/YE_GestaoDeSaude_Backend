package com.maua.yegestaodesaude.modules.Medicine.create_medicine.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maua.yegestaodesaude.shared.domain.dtos.MedicineDTO;
import com.maua.yegestaodesaude.shared.services.AutenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/medicine")
@Tag(name = "Medicine")
public class CreateMedicineController {
    
    @Autowired
    private CreateMedicineUsecase createMedicineUsecase;

    @Autowired
    private AutenticationService autenticationService;

    @PostMapping
    @Operation(summary = "Criar novo medicamento")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Medicamento criado com sucesso",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Medicamento criado com sucesso\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Erro ao criar medicamento",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Erro ao criar medicamento.\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Acesso negado",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Acesso negado\"}")
                )
            }
        ),
        @ApiResponse(
            responseCode = "422",
            description = "Informações inválidas para criar medicamento",
            content = {
                @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(example = "{\"message\": \"Informações inválidas para criar medicamento\"}")
                )
            }
        )
    })
    public ResponseEntity<Object> createMedicine(@RequestBody MedicineDTO medicineDTO, HttpServletRequest request){
        try{
            String token = extractTokenFromRequest(request);
            Long clientId = autenticationService.getClientId(token);

            if(medicineDTO == null){
                return ResponseEntity.status(422).body(new CreateMedicineViewmodel("Informações inválidas para criar medicamento"));
            }

            var result = createMedicineUsecase.execute(medicineDTO, clientId);
            return result;
        }catch(Exception e){
            return ResponseEntity.status(400).body(new CreateMedicineViewmodel(e.getMessage()));
        }
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        } else {
            throw new RuntimeException("Token not found in request headers");
        }
    }
}
