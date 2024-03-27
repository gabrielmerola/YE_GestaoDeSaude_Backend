package com.maua.yegestaodesaude.modules.auth_client.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.maua.yegestaodesaude.shared.domain.dtos.AuthDto;
import com.maua.yegestaodesaude.shared.services.AutenticationService;

@Service
public class AuthClientUsecase {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private AutenticationService autenticationService;

    public String execute(AuthDto authDto) {
        var clientAutenticationToken = new UsernamePasswordAuthenticationToken(authDto.email(), authDto.password());
        authenticationManager.authenticate(clientAutenticationToken);
        
        return autenticationService.getToken(authDto);
    }
}
