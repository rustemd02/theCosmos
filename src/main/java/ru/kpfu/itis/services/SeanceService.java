package ru.kpfu.itis.services;

import ru.kpfu.itis.models.entities.Seance;
import ru.kpfu.itis.models.entities.User;

import java.util.List;
import java.util.Optional;

public interface SeanceService {
    List<Seance> findAll();
    Optional<Seance> findById(Long id);
    Seance buyTicket (Long seanceId, User user, Boolean useCosmostar);
}
