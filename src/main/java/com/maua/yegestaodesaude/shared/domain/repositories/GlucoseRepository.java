package com.maua.yegestaodesaude.shared.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maua.yegestaodesaude.shared.domain.entities.Glucose;

@Repository
public interface GlucoseRepository extends JpaRepository<Glucose, Long> {
    List<Glucose> findAllByClientId(Long clientId);
}
