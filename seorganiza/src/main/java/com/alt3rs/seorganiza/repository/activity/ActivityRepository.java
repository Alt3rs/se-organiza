package com.alt3rs.seorganiza.repository.activity;

import com.alt3rs.seorganiza.domain.activity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {
    // Adicionar método para buscar atividades por usuário
    List<Activity> findByUserId(String userId);
}
