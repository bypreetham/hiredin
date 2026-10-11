package com.hirechart.hiredin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hirechart.hiredin.entity.Achievements;

@Repository
public interface AchievementRepository extends JpaRepository<Achievements, Long> {

}
