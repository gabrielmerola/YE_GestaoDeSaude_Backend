package com.maua.yegestaodesaude.modules.imc.delete_imc.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.repositories.ImcRepository;

@Service
public class DeleteImcUsecase {
    
    @Autowired
    private ImcRepository imcRepository;

    public ResponseEntity<Object> execute(Long id) {
        try {
            imcRepository.deleteById(id);
            return ResponseEntity.status(200).body(new DeleteImcViewmodel("Imc deletado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new DeleteImcViewmodel("Erro ao deletar imc!"));    
        }
    }

}
