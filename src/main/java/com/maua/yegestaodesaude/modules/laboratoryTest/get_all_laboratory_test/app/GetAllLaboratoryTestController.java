package com.maua.yegestaodesaude.modules.laboratoryTest.get_all_laboratory_test.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/laboratory-test")
@Tag(name = "Laboratory Test")
public class GetAllLaboratoryTestController {

    @Autowired
    private GetAllLaboratoryTestUsecase getAllLaboratoryTestUsecase;

    @GetMapping
    @Operation(summary = "Obter exames laboratoriais")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Object> getLaboratoryTest(HttpServletRequest request) {
        try {
            var result = getAllLaboratoryTestUsecase.execute();
            return result;
        } catch (Exception e) {
            return ResponseEntity.status(400).body("{\"message\": \"Erro ao obter exames laboratoriais\"}");
        }
    }
}
