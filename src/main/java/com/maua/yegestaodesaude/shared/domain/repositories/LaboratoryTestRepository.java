package com.maua.yegestaodesaude.shared.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maua.yegestaodesaude.shared.domain.entities.LaboratoryTest;

@Repository
public interface LaboratoryTestRepository extends JpaRepository<LaboratoryTest, Long>{
}
