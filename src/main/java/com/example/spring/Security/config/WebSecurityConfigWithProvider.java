package com.example.spring.Security.config;

import com.example.spring.Security.security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @사용자관리 재구성 :
 * 다른 설정방법 사용자 등록과 PassWordEncorder 대신 AuthenticationProvider를 구현하고 등록한다.
 * WebSecurityConfigurerAdapter의 configure(AuthenticationManagerBuilder)를 override 해
 * builder를 매개변수로 받아서 메소드 내에서 사용자 구성, 비밀번호 인코딩 구성을 모두 직접 처리하였다.
 * */
@Configuration
public class WebSecurityConfigWithProvider {

    //4. 예제 2.9 ~ 12 & 2.16
    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }


    //3. 엔드포인트 권한부여 구성 재정의 HttpSecurity의 매개변수로 구성 변경 예제 2.6
    @Bean
    public SecurityFilterChain config(HttpSecurity http)throws Exception{
        http.httpBasic();
        http.authorizeRequests().anyRequest().authenticated();
        return http.build();
    }

}
