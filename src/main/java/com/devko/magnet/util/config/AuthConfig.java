package com.devko.magnet.util.config;

import com.devko.magnet.repository.auth.UserRepository;
import com.devko.magnet.service.auth.NaverLoginService;
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
