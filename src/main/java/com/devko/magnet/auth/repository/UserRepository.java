package com.devko.magnet.auth.repository;

import com.devko.magnet.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findBySnsId(String id);
}
