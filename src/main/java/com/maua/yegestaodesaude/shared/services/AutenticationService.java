package com.maua.yegestaodesaude.shared.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.maua.yegestaodesaude.shared.domain.dtos.AuthDto;
import com.maua.yegestaodesaude.shared.domain.entities.Client;
import com.maua.yegestaodesaude.shared.domain.repositories.ClientRepository;

@Service
public class AutenticationService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return clientRepository.findByEmail(email);
    }
    
    public String getToken(AuthDto authDto) {
        Client client = clientRepository.findByEmail(authDto.email());

        return generateTokenJwt(client);
    }

    public String generateTokenJwt(Client client) {
        try{
            Algorithm algorithm = Algorithm.HMAC256("YE_GESTAO_KEY");

            return JWT.create()
                .withIssuer("YE_GESTAO")
                .withSubject(client.getId().toString())
                .withClaim("clientId", client.getId())
                .withClaim("email", client.getEmail())
                .withExpiresAt(generateExpiratedDate())
                .sign(algorithm);
        }catch(JWTCreationException exception){
            throw new RuntimeException("Error generating JWT token");
        }
    }

    public String validateTokenJwt(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256("YE_GESTAO_KEY");

            return JWT.require(algorithm)
                    .withIssuer("YE_GESTAO")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch(JWTVerificationException exception){
            return "";
        }
    }

    private Instant generateExpiratedDate() {
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-03:00"));
    }
}
