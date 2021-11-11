package services;

import models.Movie;
import models.Seance;
import models.User;

import java.util.List;
import java.util.Optional;

public interface SeanceService {
    List<Seance> findAll();
    Optional<Seance> findById(Long id);
    Seance buyTicket (Long seanceId, User user, Boolean useCosmostar);
}
