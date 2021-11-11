package services;

import models.Seance;
import models.User;
import repositories.SeanceRepository;
import repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

public class SeanceServiceImpl implements SeanceService{
    private SeanceRepository seanceRepository;
    private UsersRepository usersRepository;

    public SeanceServiceImpl(SeanceRepository seanceRepository, UsersRepository usersRepository) {
        this.seanceRepository = seanceRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public List<Seance> findAll() {
        return seanceRepository.findAll();
    }

    @Override
    public Optional<Seance> findById(Long id) {
        return seanceRepository.findById(id);
    }

    @Override
    public Seance buyTicket(Long seanceId, User user, Boolean useCosmostar) {
        Optional<Seance> seance = seanceRepository.findById(seanceId);
        int seancePrice = seance.get().getPrice();
        if (useCosmostar) {
            int cosmostarBalance = user.getCosmostar().getPoints();
            if (seancePrice < cosmostarBalance) {
                user.getCosmostar().setPoints(cosmostarBalance - seancePrice);
                seanceRepository.buyTicket(seanceId, user.getId());
                return seance.get();
            } else {
                return null;
            }
        } else {
            int userBalance = user.getBalance();
            if (seancePrice < userBalance) {
                user.setBalance(userBalance - seancePrice);
                seanceRepository.buyTicket(seanceId, user.getId());
                return seance.get();
            } else {
                return null;
            }
        }

    }
}
