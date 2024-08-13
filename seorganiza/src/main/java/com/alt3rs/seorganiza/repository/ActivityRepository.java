package com.alt3rs.seorganiza.repository;

import com.alt3rs.seorganiza.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {
}
