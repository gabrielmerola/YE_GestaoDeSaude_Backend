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
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(new ClientFoundException("Email ja cadastrado"));
        }

        clientExists = clientRepository.findByCpf(clientDto.cpf());
        if(clientExists != null){
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(new ClientFoundException("CPF ja cadastrado"));
        }

        clientExists = clientRepository.findByPhone(clientDto.phone());
        if(clientExists != null){
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(new ClientFoundException("Telefone ja cadastrado"));
        }

        var passwordCrypted = passwordEncoder.encode(clientDto.password());

        Client entity = new Client(clientDto.name(), clientDto.email(), passwordCrypted, clientDto.phone(), clientDto.cpf());

        if(!entity.validateCPF(clientDto.cpf())){
            return ResponseEntity.status(HttpStatusCode.valueOf(422)).body("CPF inválido");
        }
        if(!entity.validateEmail(clientDto.email())){
            return ResponseEntity.status(HttpStatusCode.valueOf(422)).body("Email inválido");
        }
        if(!entity.validatePassword(clientDto.password())){
            return ResponseEntity.status(HttpStatusCode.valueOf(422)).body("Senha inválida");
        }
        if(clientDto.name() == null || clientDto.name().isEmpty() || clientDto.name().length() < 3){
            return ResponseEntity.status(HttpStatusCode.valueOf(422)).body("Nome inválido");
        }
        if(clientDto.phone() == null || clientDto.phone().isEmpty() || clientDto.phone().length() < 11){
            return ResponseEntity.status(HttpStatusCode.valueOf(422)).body("Telefone inválido");
        }

        clientRepository.save(entity);
        
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("Cliente cadastrado com sucesso!");
    }
}
