package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.models.entities.UserSeance;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserSeanceRepository extends JpaRepository<UserSeance, Long> {


}
