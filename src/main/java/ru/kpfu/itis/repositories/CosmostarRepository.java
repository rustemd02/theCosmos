package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.models.entities.Cosmostar;
import ru.kpfu.itis.models.entities.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface CosmostarRepository extends JpaRepository<Cosmostar, Long> {

    @Query("select card from Cosmostar card inner join User u on card.id = u.cosmostar.id where u.id = ?1")
    Optional<Cosmostar> findCosmostarByUserId (Long userId);


    Optional<Cosmostar> findCosmostarById (Long cosmostarId);


}
