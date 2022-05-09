package ru.kpfu.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.models.entities.Seance;
import ru.kpfu.itis.models.entities.User;
import ru.kpfu.itis.models.entities.UserSeance;
import ru.kpfu.itis.repositories.SeanceRepository;
import ru.kpfu.itis.repositories.UserSeanceRepository;
import ru.kpfu.itis.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SeanceServiceImpl implements SeanceService {
    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserSeanceRepository userSeanceRepository;

    @Override
    public List<Seance> findAll() {
        return seanceRepository.findAll();
    }

    @Override
    public Optional<Seance> findById(Long id) {
        return seanceRepository.findById(id);
    }

    @Override
    public Seance buyTicket (Long seanceId, User user, Boolean useCosmostar) {
        Optional<Seance> seance = seanceRepository.findById(seanceId);
        int seancePrice = seance.get().getPrice();
        if (useCosmostar) {
            int cosmostarBalance = user.getCosmostar().getPoints();
            if (seancePrice < cosmostarBalance) {
                user.getCosmostar().setPoints(cosmostarBalance - seancePrice);
                insertIntoUserSeance(seanceId,user);
                return seance.get();
            } else {
                return null;
            }
        } else {
            int userBalance = user.getBalance();
            if (seancePrice < userBalance) {
                user.setBalance(userBalance - seancePrice);
                insertIntoUserSeance(seanceId, user);
                usersRepository.changeBalance(user.getId(), user.getBalance());
                return seance.get();
            } else {
                return null;
            }
        }

    }

    public void insertIntoUserSeance(Long seanceId, User user) {
        UserSeance userSeance = new UserSeance();
        userSeance.setSeanceId(seanceId);
        userSeance.setUserId(user.getId());
        userSeanceRepository.save(userSeance);
    }
}
