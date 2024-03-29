package com.maua.yegestaodesaude.modules.delete_client.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.entities.Client;
import com.maua.yegestaodesaude.shared.domain.repositories.ClientRepository;

import java.util.Optional;

@Service
public class DeleteClientUsecase {

    private final ClientRepository clientRepository;

    @Autowired
    public DeleteClientUsecase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void execute(Long clientId) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            clientRepository.delete(client);
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }
}
