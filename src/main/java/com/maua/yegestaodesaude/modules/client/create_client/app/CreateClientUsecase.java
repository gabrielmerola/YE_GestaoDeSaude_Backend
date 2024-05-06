package com.maua.yegestaodesaude.modules.client.create_client.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.dtos.ClientDto;
import com.maua.yegestaodesaude.shared.domain.entities.Client;
import com.maua.yegestaodesaude.shared.domain.repositories.ClientRepository;

@Service
public class CreateClientUsecase {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<Object> execute(ClientDto clientDto){

        Client clientExists = clientRepository.findByEmail(clientDto.email());
        if(clientExists != null){
            return ResponseEntity.status(409).body(new CreateClientViewmodel("Email ja cadastrado"));
        }

        clientExists = clientRepository.findByCpf(clientDto.cpf());
        if(clientExists != null){
            return ResponseEntity.status(409).body(new CreateClientViewmodel("CPF ja cadastrado"));
        }

        clientExists = clientRepository.findByPhone(clientDto.phone());
        if(clientExists != null){
            return ResponseEntity.status(409).body(new CreateClientViewmodel("Telefone ja cadastrado"));
        }

        var passwordCrypted = passwordEncoder.encode(clientDto.password());

        Client entity = new Client(clientDto.name(), clientDto.email(), passwordCrypted, clientDto.phone(), clientDto.cpf());

        // if(!entity.validateCPF(clientDto.cpf())){
        //     return ResponseEntity.status(422).body(new CreateClientViewmodel("CPF inválido"));
        // }
        if(!entity.validateEmail(clientDto.email())){
            return ResponseEntity.status(422).body(new CreateClientViewmodel("Email inválido"));
        }
        if(!entity.validatePassword(clientDto.password())){
            return ResponseEntity.status(422).body(new CreateClientViewmodel("Senha inválida"));
        }
        if(clientDto.name() == null || clientDto.name().isEmpty() || clientDto.name().length() < 3){
            return ResponseEntity.status(422).body(new CreateClientViewmodel("Nome inválido"));
        }
        if(clientDto.phone() == null || clientDto.phone().isEmpty()){
            return ResponseEntity.status(422).body(new CreateClientViewmodel("Telefone inválido"));
        }

        clientRepository.save(entity);
        
        return ResponseEntity.status(201).body(new CreateClientViewmodel("Cliente cadastrado com sucesso!"));
    }
}
