package com.maua.yegestaodesaude.shared.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maua.yegestaodesaude.shared.domain.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
    Client findByEmail(String email);
    Client findByCpf(String cpf);
    Client findByPhone(String phone);
}
