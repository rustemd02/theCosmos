package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.models.entities.Cosmostar;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface CosmostarRepository extends JpaRepository<Cosmostar, Long> {

    @Query("select card from Cosmostar card inner join User u on card.user.id = u.id where u.id = ?1")
    Optional<Cosmostar> findCosmostarByUserId(Long userId);


    Optional<Cosmostar> findCosmostarById(Long cosmostarId);

    @Modifying
    @Query("update Cosmostar as c set c.points = ?2 where c.id = ?1")
    void updatePoints(Long cosmostarId, int points);


}
