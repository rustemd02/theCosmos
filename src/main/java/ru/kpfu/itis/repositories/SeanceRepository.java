package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.models.entities.Seance;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface SeanceRepository extends JpaRepository<Seance, Long> {
    List<Seance> findAll();

    Optional<Seance> findById(Long seanceId);

    @Query("select s from Seance s inner join UserSeance us on s.id = us.seanceId where us.userId = ?1")
    Optional<Seance> findByUserId(Long userId);
}
