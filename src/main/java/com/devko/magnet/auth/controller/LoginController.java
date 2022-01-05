package com.devko.magnet.auth.controller;

import com.devko.magnet.auth.dto.LoginUser;
import com.devko.magnet.auth.service.NaverLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class LoginController {
    private final NaverLoginService loginService;

    @PostMapping("auth/{type}")
    public ResponseEntity<Map<String, Object>> login(@PathVariable("type") String type, @RequestParam Map<String, String> params){
        LoginUser user = new LoginUser();
        ResponseEntity<Map<String, Object>> responseEntity;
        switch (type){
            case "naver" :
                Map<String, String> access = loginService.getAccessToken(params.get("code"), params.get("state"));
                user = loginService.getUserInfo(access.get("access_token"));
                user.setRefreshToken(access.get("refresh_token"));
        }

        user.setLoginType(type);
        if(user.getId() == null)
            responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else {
            responseEntity = loginService.isNewUser(user);
        }
        return responseEntity;
    }

    @RequestMapping("logout/{provider}/{userId}")
    public ResponseEntity logout(@PathVariable String provider, @PathVariable Long userId){
        String accessToken = loginService.renewalAccessToken(userId);
        loginService.logout(accessToken);
        return new ResponseEntity(HttpStatus.OK);
    }
}
