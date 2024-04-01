package com.maua.yegestaodesaude.modules.get_client.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.repositories.ClientRepository;

@Service
public class GetClientUsecase {

    @Autowired
    private ClientRepository clientRepository;

    public GetClientViewmodel execute(Long idClient) {

        var client = this.clientRepository.findById(idClient).orElseThrow(() -> {
            throw new UsernameNotFoundException("Client not found");
        });

        var clientDTO = GetClientViewmodel.builder()
                .id(client.getId().toString())
                .name(client.getName())
                .cpf(client.getCpf())
                .email(client.getEmail())
                .phone(client.getPhone())
                .build();
        return clientDTO;
    }
}
