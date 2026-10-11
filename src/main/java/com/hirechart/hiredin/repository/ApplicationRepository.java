package com.hirechart.hiredin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hirechart.hiredin.entity.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    boolean existsByCandidateIdAndJobId(Long candidateId, Long jobId);

    Optional<Application> findByCandidateIdAndJobId(Long candidateId, Long jobId);

    List<Application> findByCandidateId(Long candidateId);

    List<Application> findByCandidateIdAndStatus(Long candidateId, String status);

    List<Application> findByCandidateIdAndStatusIgnoreCase(Long candidateId, String status);

    List<Application> findByJobId(Long jobId);

    List<Application> findByJobIdAndStatus(Long jobId, String status);

    List<Application> findByJobIdAndStatusIgnoreCase(Long jobId, String status);

    List<Application> findByJobIdAndJobCompanyId(Long jobId, Long companyId);

    List<Application> findByJobIdAndJobCompanyIdAndStatus(Long jobId, Long companyId, String status);
}
