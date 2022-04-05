package com.example.sd_assignment_2.persistance;

import com.example.sd_assignment_2.business.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsername(String username);

    List<User> findByUsernameAndPassword(String username, String password);

    @Query("select u from User u where u.id = ?1")
    User findOne(Long id);
}
