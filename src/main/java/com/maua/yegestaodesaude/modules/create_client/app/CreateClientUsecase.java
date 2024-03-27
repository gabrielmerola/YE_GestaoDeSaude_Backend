package com.maua.yegestaodesaude.modules.create_client.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.dtos.ClientDto;
import com.maua.yegestaodesaude.shared.domain.entities.Client;
import com.maua.yegestaodesaude.shared.domain.repositories.ClientRepository;
import com.maua.yegestaodesaude.shared.helpers.exceptions.ClientFoundException;

@Service
public class CreateClientUsecase {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<Object> execute(ClientDto clientDto){

        Client clientExists = clientRepository.findByEmail(clientDto.email());

        if(clientExists != null){
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(new ClientFoundException("Usuário ja cadastrado"));
        }

        var passwordCrypted = passwordEncoder.encode(clientDto.password());

        Client entity = new Client(clientDto.name(), clientDto.email(), passwordCrypted, clientDto.phone(), clientDto.cpf());
        Client newClient = clientRepository.save(entity);
        
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(new ClientDto(newClient.getName(), newClient.getEmail(), newClient.getPassword(), newClient.getPhone(), newClient.getCpf()));
    }
}
