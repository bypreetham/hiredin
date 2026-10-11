package com.hirechart.hiredin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hirechart.hiredin.entity.Experience;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {

}
