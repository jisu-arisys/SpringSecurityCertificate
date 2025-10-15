package com.example.spring.Security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @사용자관리 재구성 :
 * userDetailsService을 사용하지 않고 직접 재구성하면
 * 콘솔에 자동생성 암호 출력되지 않음.
 * 사용자 등록과 PassWordEncorder도 재 정의해야함
 * */
//@Configuration
public class WebSecurityConfig {


    //1. 자격증명 있는 사용자 생성, UserDetailsService에 등록 예제 2.3 ~ 2.4
    /**-> var 제거 */
    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
        userDetailsService.createUser(User.withUsername("asha")
                .password("aaaa")
                .build());

        return userDetailsService;
    }

    //2. 암호를 저장, 검증하는 클래스 PassWordEncorder 정의 예제 2.5
    /**
    * noOpPasswordEncoder는 test용으로만 사용해야함
    * 주로 PBKDF2를 이용한다.
    * return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    //3. 엔드포인트 권한부여 구성 재정의 HttpSecurity의 매개변수로 구성 변경 예제 2.6
    /** Deprecated된 WebSecurityConfigurerAdapter 대체방법
     * 상속 후 configure() override 대신
     * SecurityFilterChain를 Bean으로 등록하는 방식으로 수정.
    * 6.1부터 Deprecated된 httpBasic(), csrf()
    * HttpBasic은 보안상의 문제로 Oauth2나 Jwt를 사용하는 추세
    * */
    @Bean
    public SecurityFilterChain config(HttpSecurity http)throws Exception{
        http.httpBasic();
        http.authorizeRequests().anyRequest().authenticated(); //모든요청 인증필요 예제 2.7
//        http.authorizeRequests().anyRequest().permitAll(); //모든요청 인증 불필요 예제 2.8
        return http.build();
    }


}
