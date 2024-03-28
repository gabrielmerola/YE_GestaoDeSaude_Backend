package com.maua.yegestaodesaude.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.maua.yegestaodesaude.shared.domain.entities.Client;
import com.maua.yegestaodesaude.shared.domain.repositories.ClientRepository;
import com.maua.yegestaodesaude.shared.services.AutenticationService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFIlter extends OncePerRequestFilter {

    @Autowired
    private AutenticationService autenticationService;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("Filtrando requisição...");

        String token = extractHeaderToken(request);

        if (token != null) {
            System.out.println("Token JWT encontrado na requisição: " + token);

            String email = autenticationService.getEmail(token);
            System.out.println("Email extraído do token JWT: " + email);

            Long clientId = autenticationService.getClientId(token);
            System.out.println("Id do cliente extraído do token JWT: " + clientId);

            Client client = clientRepository.findByEmail(email);

            if (client != null) {
                System.out.println("Cliente encontrado pelo email: " + email);

                var autentication = new UsernamePasswordAuthenticationToken(client, null, client.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(autentication);
                System.out.println("Autenticação do Spring Security configurada com sucesso.");
            } else {
                System.out.println("Cliente não encontrado pelo email: " + email);
            }
        }

        filterChain.doFilter(request, response);
        System.out.println("Filtro concluído.");
    }

    public String extractHeaderToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            return null;
        }

        if (!authHeader.startsWith("Bearer ")) {
            return null;
        }

        return authHeader.substring(7);
    }
}
