package com.maua.yegestaodesaude.modules.imc.get_imc.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.entities.Imc;
import com.maua.yegestaodesaude.shared.domain.repositories.ImcRepository;
import com.maua.yegestaodesaude.shared.utils.DateUtils;

@Service
public class GetImcUsecase {

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private ImcRepository imcRepository;

    public ResponseEntity<Object> execute(Long clientId) {
        List<Imc> imc = imcRepository.findAllByClientId(clientId);

        if (imc.isEmpty()) {
            return ResponseEntity.status(204).body("{\"message\": \"Não encontrado\"}");
        }

        List<GetImcViewmodel> imcList = new ArrayList<>();

        for (Imc gi : imc) {
            imcList.add(GetImcViewmodel.builder()
                    .id(gi.getId())
                    .weight(gi.getWeight())
                    .height(gi.getHeight())
                    .imc(gi.getImc())
                    .level(gi.getLevel())
                    .date(dateUtils.RevertDate(gi.getDate()))
                    .build());
        }
        return ResponseEntity.status(200).body(imcList);
    }
}
