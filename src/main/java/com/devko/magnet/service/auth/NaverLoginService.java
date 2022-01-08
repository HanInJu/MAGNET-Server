package com.devko.magnet.service.auth;

import com.devko.magnet.model.entity.User;
import com.devko.magnet.dto.auth.LoginUser;
import com.devko.magnet.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Optional;

@Service
public class NaverLoginService extends LoginService{
    String tokenUri = "https://nid.naver.com/oauth2.0/token";
    private String[] grantTypes = {"authorization_code", "refresh_token", "delete"};

    public NaverLoginService(UserRepository userRepository) {
        super(userRepository);
    }

    public void setParam(){
        params = new LinkedMultiValueMap<>();
        params.add("client_id", "uOV9tpI3J7iFOOG6YOOz");
        params.add("client_secret", "PUPjLJW41g");
    }

    @Override
    public Map<String, String> getAccessToken(String code, String state){
        setParam();
        params.add("grant_type", grantTypes[0]);
        params.add("code", code);
        params.add("state", state);

        Map<String, String> response = WebClient.create(tokenUri)
                        .post()
                        .body(BodyInserters.fromFormData(params))
                        .retrieve()
                        .bodyToMono(Map.class)
                        .block();
        return response;
    }

    @Override
    public LoginUser getUserInfo(String accessToken) {
        String userInfoUri = "https://openapi.naver.com/v1/nid/me";
        LoginUser user;

        Map<String, Object> res = WebClient.create(userInfoUri)
                .post()
                .header("Authorization", String.format("Bearer %s", accessToken))
                .retrieve()
                .bodyToMono(Map.class)
                .block();
        Map<String, String> body = (Map<String, String>) res.get("response");

        if(res.get("resultcode").equals("00"))
            user = mapper.convertValue(body, LoginUser.class);
        else
            user = new LoginUser();
        return user;
    }

    @Override
    public String renewalAccessToken(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        setParam();
        params.add("grant_type", grantTypes[1]);
        params.add("refresh_token", user.get().getSnsRefreshToken());
        Map<String, String> res = WebClient.create(tokenUri)
                .post()
                .body(BodyInserters.fromFormData(params))
                .retrieve()
                .bodyToMono(Map.class)
                .block();
        // TODO :: 예외처리
        return res.get("access_token");
    }

    @Override
    public void logout(String accessToken) {
        setParam();
        params.add("grant_type", grantTypes[2]);
        params.add("access_token", accessToken);
        params.add("service_provider", "NAVER");
        Map<String, String> res = WebClient.create(tokenUri)
                .get()
                .uri(uriBuilder -> uriBuilder.queryParams(params).build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
}
