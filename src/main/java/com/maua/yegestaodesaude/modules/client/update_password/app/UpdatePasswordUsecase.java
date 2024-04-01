package com.maua.yegestaodesaude.modules.client.update_password.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.entities.Client;
import com.maua.yegestaodesaude.shared.domain.repositories.ClientRepository;
import com.maua.yegestaodesaude.shared.helpers.errors.EntityError;

@Service
public class UpdatePasswordUsecase {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Client execute(String email, String newPassword) {
        Client client = clientRepository.findByEmail(email);

        if (client == null) {
            throw new EntityError("email");
        }

        String currentPassword = client.getPassword();
        if (passwordEncoder.matches(newPassword, currentPassword)) {
            throw new EntityError("newPassword, precisa ser diferente da senha atual,");
        }

        var passwordCrypted = passwordEncoder.encode(newPassword);

        client.setPassword(passwordCrypted);
        return clientRepository.save(client);
    }
}
