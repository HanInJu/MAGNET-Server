package com.devko.magnet.repository.auth;

import com.devko.magnet.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findBySnsId(String id);
}
