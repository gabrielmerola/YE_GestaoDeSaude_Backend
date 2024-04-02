package com.maua.yegestaodesaude.modules.client.update_password.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Object> execute(String email, String newPassword) {
        Client client = clientRepository.findByEmail(email);

        if (client == null) {
            return ResponseEntity.status(422).body(new EntityError("email"));
        }

        String currentPassword = client.getPassword();
        if (passwordEncoder.matches(newPassword, currentPassword)) {
            return ResponseEntity.status(422).body(new EntityError("newPassword, precisa ser diferente da senha atual"));
        }

        var passwordCrypted = passwordEncoder.encode(newPassword);

        client.setPassword(passwordCrypted);
        return ResponseEntity.status(200).body(new UpdatePasswordViewmodel("Senha atualizada com sucesso"));
    }
}
