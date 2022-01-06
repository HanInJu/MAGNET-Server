package com.devko.magnet.auth.controller;

import com.devko.magnet.auth.domain.User;
import com.devko.magnet.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @GetMapping("{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId){
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }
}
