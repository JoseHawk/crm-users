package com.joselara.crmusers.repositories;

import com.joselara.crmusers.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findFirstByEmail(String email);
}
