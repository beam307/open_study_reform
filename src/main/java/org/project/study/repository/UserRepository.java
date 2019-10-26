package org.project.study.repository;

import org.project.study.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByIdAndEmail(Long id, String email);

    @Modifying
    @Query("UPDATE User u set u.active = 0 where u.id = ?1")
    void updateUserActive(Long userId);

    List<User> findByIdIn(List<Long> userIds);

}
