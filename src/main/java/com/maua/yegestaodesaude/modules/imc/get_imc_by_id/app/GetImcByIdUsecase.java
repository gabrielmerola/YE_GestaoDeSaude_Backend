package com.maua.yegestaodesaude.modules.imc.get_imc_by_id.app;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.entities.Imc;
import com.maua.yegestaodesaude.shared.domain.repositories.ImcRepository;

@Service
public class GetImcByIdUsecase {

    @Autowired
    private ImcRepository imcRepository;
    
    public ResponseEntity<Object> execute(Long id) {
        Optional<Imc> imcOptional = imcRepository.findById(id);

        if(imcOptional.isPresent()){
            Imc imc = imcOptional.get();
            GetImcByIdViewmodel viewModel = GetImcByIdViewmodel.builder()
                    .id(imc.getId())
                    .weight(imc.getWeight())
                    .height(imc.getHeight())
                    .imc(imc.getImc())
                    .level(imc.getLevel())
                    .date(imc.getDate().toString())
                    .build();
            return ResponseEntity.status(200).body(viewModel);
        }else{
            return ResponseEntity.status(204).body("{\"message\": \"IMC não encontrado\"}");
        }
    }

}
