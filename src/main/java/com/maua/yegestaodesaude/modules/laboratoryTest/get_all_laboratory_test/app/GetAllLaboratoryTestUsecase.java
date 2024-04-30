package com.maua.yegestaodesaude.modules.laboratoryTest.get_all_laboratory_test.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.maua.yegestaodesaude.shared.domain.entities.LaboratoryTest;

import com.maua.yegestaodesaude.shared.domain.repositories.LaboratoryTestRepository;

@Service
public class GetAllLaboratoryTestUsecase {
    
    @Autowired
    private LaboratoryTestRepository laboratoryTestRepository;

    public ResponseEntity<Object> execute() {
        List<LaboratoryTest> laboratoryTest = laboratoryTestRepository.findAll();

        if (laboratoryTest.isEmpty()) {
            return ResponseEntity.status(204).body("{\"message\": \"Não encontrado\"}");
        }

        List<GetAllLaboratoryTestViewmodel> laboratoryTestList = new ArrayList<>();

        for (LaboratoryTest glt : laboratoryTest) {
            laboratoryTestList.add(GetAllLaboratoryTestViewmodel.builder()
                    .id(glt.getId())
                    .textName(glt.getTextName())
                    .rangeRefMin(glt.getRangeRefMin())
                    .rangeRefMax(glt.getRangeRefMax())
                    .unitOfMeasurement(glt.getUnitOfMeasurement())
                    .build());
        }
        return ResponseEntity.status(200).body(laboratoryTestList);
    }
}
