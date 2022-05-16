package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.models.entities.Seance;
import ru.kpfu.itis.models.entities.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Modifying
    @Query("update User as u set u.balance = ?2 where u.id = ?1")
    User changeBalance(Long userId, int newBalance);

    boolean existsByEmail(String email);

    @Modifying
    @Query("update User as u set u.profilePic = ?2 where u.id = ?1")
    void setProfilePic(Long id, String fileName);
}
