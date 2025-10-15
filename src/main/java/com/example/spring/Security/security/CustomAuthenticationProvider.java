package com.example.spring.Security.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/** @인증 공급자 인터페이스 구현 : 추상메소드의 논리를 구현함.
 * @인증 공급자의 역할 : 인증 관리자로 부터 인증책임을 위임받는다.
 * 인증 논리를 구현한다.
 * 사용자 찾기 작업은 userDetailsService에
 * 암호 검증 작업은 PasswordEncorder에 위임한다.
 * 이러한 세분화된 책임과 느슨한 결합을 갖는 설계는 스프링시큐리티가 유연하고 통합하기 쉽게 만든다.
 * */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    //1. AuthenticationProvider 구현 재정의 예제 2.13 - 2.15
    /** 전달받은 Authentication 형식이 맞는지 확인
     * */
    @Override
    public boolean supports(Class<?> authenticationType) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationType);
    }

    /**  @인증 논리 추가 :
     * userDetailsService 의 사용자 찾기 작업 대체 & PasswordEncorder 의 암호인증 작업 대체 하도록 구현하였다.
     * */
    @Override
    public Authentication authenticate(Authentication authentication) throws org.springframework.security.core.AuthenticationException {

        String userName = authentication.getName(); //Principal 인터페이스 메소드를 상속받은 Authentication
        String password = String.valueOf(authentication.getCredentials());

        if("asha".equals(userName) && "1111".equals(password)){
            return new UsernamePasswordAuthenticationToken(userName, password, Arrays.asList());
        }

        else{
         throw new AuthenticationCredentialsNotFoundException("Error in authentication. Please Check ID and PassWord");
        }
    }


}