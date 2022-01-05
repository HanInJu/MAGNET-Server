package com.devko.magnet.auth.config;

import com.devko.magnet.auth.repository.UserRepository;
import com.devko.magnet.auth.service.NaverLoginService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {
    private final UserRepository userRepository;

    public AuthConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public NaverLoginService naverLoginService(){
        return new NaverLoginService(userRepository);
    }
}
