package com.hirechart.hiredin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hirechart.hiredin.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByCompanyId(Long companyId);

    List<Job> findByCompanyIdAndStatusIgnoreCase(Long companyId, String status);

    List<Job> findByStatusIgnoreCase(String status);
}
