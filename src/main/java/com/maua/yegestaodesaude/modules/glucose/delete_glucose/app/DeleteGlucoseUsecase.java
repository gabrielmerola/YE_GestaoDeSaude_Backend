package com.maua.yegestaodesaude.modules.glucose.delete_glucose.app;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.entities.Glucose;
import com.maua.yegestaodesaude.shared.domain.repositories.GlucoseRepository;

@Service
public class DeleteGlucoseUsecase {

    @Autowired
    private GlucoseRepository glucoseRepository;
    
    public ResponseEntity<Object> execute(Long id) {
        Optional<Glucose> glucoseOptional = glucoseRepository.findById(id);

        if(!glucoseOptional.isPresent()){
            return ResponseEntity.status(204).body(new DeleteGlucoseViewmodel("Registro de pressão arterial não encontrado!"));
        }

        Glucose glucose = glucoseOptional.get();
        glucoseRepository.delete(glucose);
        return ResponseEntity.status(200).body(new DeleteGlucoseViewmodel("Registro de pressão arterial deletado com sucesso!"));
    }
}
