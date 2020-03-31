package com.pusulait.multithreading.repository;

import com.pusulait.multithreading.model.dto.RuleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleDTORepository extends JpaRepository<RuleDTO, Long> {

}
