package org.selfproject.cinema_app.repository;

import org.selfproject.cinema_app.model.BiletAlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatsRepository extends JpaRepository<BiletAlEntity, Long> {
    Optional<BiletAlEntity> findTopByOrderByIdDesc();
}