package ru.kpfu.itis.services.interfaces;

import ru.kpfu.itis.models.entities.Cosmostar;

import java.util.Optional;


public interface CosmostarService {
    Optional<Cosmostar> findCardByUserId(Long userId);
}
