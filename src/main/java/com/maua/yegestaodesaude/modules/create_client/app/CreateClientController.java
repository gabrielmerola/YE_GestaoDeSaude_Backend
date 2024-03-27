package com.maua.yegestaodesaude.modules.create_client.app;

import com.maua.yegestaodesaude.shared.domain.dtos.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class CreateClientController {

    @Autowired
    private CreateClientUsecase createClientUsecase;

    @PostMapping
    public ResponseEntity<Object> createClient(@RequestBody ClientDto clientDto){
        try{
            var result = this.createClientUsecase.execute(clientDto);
            return result;
        }catch(Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
