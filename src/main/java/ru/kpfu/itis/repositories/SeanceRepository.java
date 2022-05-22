package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("SELECT s from Seance s join fetch User u on s member of u.seances where u.id = ?1")
    List<Seance> findSeancesByUserId(Long userId);

    @Modifying
    @Query("update Seance as s set s.ticketsAmount = ?2 where s.id = ?1")
    void setTicketsAmount(Long seanceId, int newTicketsAmount);

    List<Seance> findByMovieId(Long movieId);
}
