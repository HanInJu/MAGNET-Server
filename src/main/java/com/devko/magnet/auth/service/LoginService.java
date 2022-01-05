package com.devko.magnet.auth.service;

import com.devko.magnet.auth.domain.User;
import com.devko.magnet.auth.dto.LoginUser;
import com.devko.magnet.auth.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

abstract public class LoginService {
    protected UserRepository userRepository;
    protected final ObjectMapper mapper = new ObjectMapper();
    protected MultiValueMap<String, String> params;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    abstract public Map<String, String> getAccessToken(String code, String state);
    abstract public LoginUser getUserInfo(String accessToken);
    abstract public String renewalAccessToken(Long userId);
    abstract public void logout(String refreshToken);

    public ResponseEntity<Map<String, Object>> isNewUser(LoginUser loginUser){
        Optional<User> user = userRepository.findBySnsId(loginUser.getId());
        Map<String, Object> info = new HashMap<>();
        HttpStatus httpStatus = HttpStatus.OK;
        boolean isNew = false;
        if(user.isEmpty()){
            user = Optional.of(userRepository.save(new User(loginUser)));
            httpStatus = HttpStatus.CREATED;
            isNew = true;
        }
        else{
            user.get().resetUserInfo(loginUser);
            userRepository.save(user.get());
        }
        info.put("userId", user.get().getId());
        info.put("newMember", isNew);
        return new ResponseEntity<>(info, httpStatus);
    }
}
