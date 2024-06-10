package org.selfproject.cinema_app.repository;

import org.selfproject.cinema_app.model.BiletAlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BiletAlRepository extends JpaRepository<BiletAlEntity, Long> {
    Optional<BiletAlEntity> findTopByOrderByIdDesc();

    @Query("SELECT b FROM BiletAlEntity b WHERE b.secilenFilm = :secilenFilm AND b.secilenSinema = :secilenSinema AND b.secilenTarih = :secilenTarih AND b.secilenSeans = :secilenSeans")
    List<BiletAlEntity> findBySelections(@Param("secilenFilm") Long secilenFilm, @Param("secilenSinema") String secilenSinema, @Param("secilenTarih") String secilenTarih, @Param("secilenSeans") String secilenSeans);
}