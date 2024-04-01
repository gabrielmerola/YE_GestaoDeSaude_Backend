package com.maua.yegestaodesaude.modules.client.auth_client.app;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maua.yegestaodesaude.shared.domain.dtos.AuthDto;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/auth")
@Tag(name = "Client")
public class AuthClientController {

    @Autowired
    private AuthClientUsecase authClientUsecase;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AuthClientViewmodel authClient(@RequestBody AuthDto authdto) {
        String token = this.authClientUsecase.execute(authdto);
        return new AuthClientViewmodel(token);
    }
}
