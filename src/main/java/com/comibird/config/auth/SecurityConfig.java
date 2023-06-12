package com.comibird.config.auth;

import com.comibird.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // 시큐리티 관련 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면 사용
                .and()
                .authorizeRequests() // URL별 권한 관리
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/") // 로그아웃 성공 시 redirectURL
                .and()
                .oauth2Login() // OAuth2 로그인 기능 설정의 진입점
                .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보 가져올 때의 설정 담당
                .userService(customOAuth2UserService); // -> userService 인터페이스의 구현체를 등록해 리소스 서버에서 가져온 사용자 정보를 이용할 수 있음
    }
}
