package com.maua.yegestaodesaude.modules.client.delete_client.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Object> execute(Long clientId) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            clientRepository.delete(client);
            return ResponseEntity.ok().body(new DeleteClientViewmodel("Cliente deletado com sucesso"));
        } else if(clientOptional.isEmpty()) {
            return ResponseEntity.status(204).body(new DeleteClientViewmodel("Cliente não encontrado"));
        }else {
            return ResponseEntity.status(400).body(new DeleteClientViewmodel("Erro ao deletar cliente"));
        }
    }
}
