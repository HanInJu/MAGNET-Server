package com.devko.magnet.auth.service;

import com.devko.magnet.auth.domain.User;
import com.devko.magnet.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User getUser(long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            // TODO :: 예외처리
            return new User();
        else
            return user.get();
    }
}
