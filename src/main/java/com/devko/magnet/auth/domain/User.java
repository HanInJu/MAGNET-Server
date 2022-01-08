package com.devko.magnet.auth.domain;

import com.devko.magnet.auth.dto.LoginUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String userName;

    @Column(name = "email", unique = true)
    private String email;

    @JsonIgnore
    @NonNull
    private String password;

    @NonNull
    private String nickname;

    private Date birthday;

    private String phone;

    private String sex;

    @Column(name = "profile_url")
    private String profileURL;

    @Column(name = "github_url")
    private String githubURL;

    @Column(name = "blog_url")
    private String blogURL;

    @Column(name = "contact_url")
    private String contactURL;

    @Column(name = "final_background")
    private String finalBackground;

    private String job;

    private String field;

    private Character status;

    @JsonIgnore
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @Column(name = "sns_type")
    private String snsType;

    @JsonIgnore
    @Column(name = "sns_id")
    private String snsId;

    @JsonIgnore
    @Column(name = "sns_refresh_token")
    private String snsRefreshToken;

    @JsonIgnore
    @Column(name = "sns_connected_at")
    private LocalDateTime snsConnectedAt;

    public User(LoginUser loginUser){
        this.userName = loginUser.getName();
        this.nickname = loginUser.getNickname();
        this.email = loginUser.getEmail();
        this.sex = loginUser.getGender();
        this.birthday = java.sql.Date.valueOf(loginUser.getBirthyear() + "-" + loginUser.getBirthday());
        this.profileURL = loginUser.getProfile_image();
        this.phone = loginUser.getMobile();
        this.snsType = loginUser.getLoginType();
        this.snsId = loginUser.getId();
        this.snsRefreshToken = loginUser.getRefreshToken();
        this.snsConnectedAt = LocalDateTime.now();
    }

    public void resetUserInfo(LoginUser loginUser){
        this.userName = loginUser.getName();
        this.email = loginUser.getEmail();
        this.phone = loginUser.getMobile();
        this.snsRefreshToken = loginUser.getRefreshToken();
    }
}
