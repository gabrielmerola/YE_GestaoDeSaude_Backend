package com.maua.yegestaodesaude.shared.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maua.yegestaodesaude.shared.domain.entities.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findAllByClientId(Long clientId);
}
