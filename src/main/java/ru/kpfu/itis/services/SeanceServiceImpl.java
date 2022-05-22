package ru.kpfu.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.models.entities.*;
import ru.kpfu.itis.repositories.*;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class SeanceServiceImpl implements SeanceService {
    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private CosmostarRepository cosmostarRepository;


    @Override
    public List<Seance> findAll() {
        List<Seance> seances = seanceRepository.findAll();
        seanceConfigure(seances);
        return seances;
    }

    @Override
    public void seanceConfigure(List<Seance> seances) {
        for (Seance seance : seances) {
            Optional<Movie> movie = moviesRepository.findMovieBySeanceId(seance.getId());
            movie.get().setSeances(null);
            Optional<Director> director = directorRepository.findDirectorByMovieId(movie.get().getId());
            setDirectorMovies(director.orElse(null));
            movie.get().setDirector(director.orElse(null));

            Optional<Genre> genre = genreRepository.findGenreByMovieId(movie.get().getId());
            movie.get().setGenre(genre.orElse(null));
            setGenreMovies(genre.orElse(null));

            seance.setMovie(movie.orElse(null));
        }

    }

    @Override
    public List<Seance> findSeancesByUserId(Long userId) {
        return seanceRepository.findSeancesByUserId(userId);
    }

    @Override
    public Optional<Seance> findById(Long id) {
        return seanceRepository.findById(id);
    }

    private void setDirectorMovies(Director director) {
        if (director == null) return;
        director.setMovies(null);
    }

    private void setGenreMovies(Genre genre) {
        if (genre == null) return;
        genre.setMovies(null);
    }

    @Override
    public Seance buyTicket(Long seanceId, User user, Boolean useCosmostar) {
        Optional<Seance> seance = seanceRepository.findById(seanceId);
        int seancePrice = seance.get().getPrice();
        if (useCosmostar) {
            Cosmostar cosmostar = cosmostarRepository.findCosmostarByUserId(user.getId()).orElse(null);
            if (cosmostar == null) return null;
            int cosmostarBalance = cosmostar.getPoints();
            if (seancePrice <= cosmostarBalance) {
                cosmostarRepository.updatePoints(cosmostar.getId(), cosmostar.getPoints() - seancePrice);
                insertIntoUserSeance(seance.get(), user);
                seanceRepository.setTicketsAmount(seanceId, seance.get().getTicketsAmount() - 1);
                return seance.get();
            } else {
                return null;
            }
        } else {
            int userBalance = user.getBalance();
            if (seancePrice <= userBalance) {
                user.setBalance(userBalance - seancePrice);
                insertIntoUserSeance(seance.get(), user);
                usersRepository.changeBalance(user.getId(), user.getBalance());
                seanceRepository.setTicketsAmount(seanceId, seance.get().getTicketsAmount() - 1);
                return seance.get();
            } else {
                return null;
            }
        }

    }

    public void insertIntoUserSeance(Seance seance, User user) {
        user.setSeances(seanceRepository.findSeancesByUserId(user.getId()));
        //Hibernate.initialize(user.getSeances());
        user.getSeances().add(seance);
        usersRepository.save(user);

    }
}
