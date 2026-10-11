package com.hirechart.hiredin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hirechart.hiredin.entity.Candidate;


@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>{

}
