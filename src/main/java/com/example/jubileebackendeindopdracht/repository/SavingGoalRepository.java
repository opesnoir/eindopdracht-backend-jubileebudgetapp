package com.example.jubileebackendeindopdracht.repository;

import com.example.jubileebackendeindopdracht.models.SavingGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SavingGoalRepository extends JpaRepository<SavingGoal, Long> {

}
