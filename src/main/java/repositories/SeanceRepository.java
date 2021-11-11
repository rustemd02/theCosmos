package repositories;

import models.Seance;

import java.util.Optional;

public interface SeanceRepository extends CrudRepository<Seance>{
    void buyTicket (Long seanceId, Long userId);

}
