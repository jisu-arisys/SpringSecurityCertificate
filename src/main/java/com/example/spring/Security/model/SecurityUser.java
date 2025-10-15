package com.example.spring.Security.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/** JPA 엔티티를 정의한 User에 보안관련 사항을 추가해 래핑하는 Security Class이다. */
public class SecurityUser implements UserDetails {

    //반드시 User 엔티티가 필요하다는 의존성을 주입함.
    private final User user;

    public SecurityUser(User user) {
        this.user = user;
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //1인당 1개의 권한만 가진다고 가정함.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(()-> user.getAuthority());
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
