package com.example.jubileebackendeindopdracht.repository;

import com.example.jubileebackendeindopdracht.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository <Profile, Long> {

}
