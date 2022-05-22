package ru.kpfu.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.models.entities.Director;
import ru.kpfu.itis.repositories.DirectorRepository;

import java.util.Optional;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public Optional<Director> findDirectorByMovieId(Long movieId) {
        return directorRepository.findDirectorByMovieId(movieId);
    }
}
