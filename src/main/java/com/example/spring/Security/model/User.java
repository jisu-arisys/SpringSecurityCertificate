package com.example.spring.Security.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @JPA앤티티 : 지속성 관련 사항과 Variable에 관한 설정*/
@Entity
@Getter
@RequiredArgsConstructor
public class User {

    @NotNull(message = "please check the ID and PW")
    private String userName = null;
    @NotBlank(message = "please check the ID and PW")
    private String password;
    private String authority;

}
