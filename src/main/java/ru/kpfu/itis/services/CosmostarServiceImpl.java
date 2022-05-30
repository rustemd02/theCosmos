package ru.kpfu.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.models.entities.Cosmostar;
import ru.kpfu.itis.repositories.CosmostarRepository;
import ru.kpfu.itis.services.interfaces.CosmostarService;

import java.util.Optional;

@Service
public class CosmostarServiceImpl implements CosmostarService {
    @Autowired
    private CosmostarRepository cosmostarRepository;

    @Override
    public Optional<Cosmostar> findCardByUserId(Long userId) {
        return cosmostarRepository.findCosmostarByUserId(userId);
    }
}
