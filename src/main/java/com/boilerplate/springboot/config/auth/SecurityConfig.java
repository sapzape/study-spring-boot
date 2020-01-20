package com.boilerplate.springboot.config.auth;

import com.boilerplate.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
// Spring Security 설정 활성화
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // h2-console 화면을 사용하기 위한 처리
            .csrf().disable().headers().frameOptions().disable()
            .and()
                // URL별 권한 관리를 설정하는 옵션의 시작점
                .authorizeRequests()
                // 권한 관리 대상을 지정하는 옵션
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/he-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // 설정된 값들 이외 나머지 URL은 모두 인증된 사용자들에게 허용
                .anyRequest().authenticated()
            .and()
                // 로그아웃 기능에 대한 여러 설정의 진입점 (로그아웃 성공시 / 주소로 이동)
                .logout().logoutSuccessUrl("/")
            .and()
                // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                .oauth2Login()
                    // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                    .userInfoEndpoint()
                        // 로그인 성공시 후속 조치를 실행할 UserService 인터페이스의 구현체 등록
                        .userService(customOauth2UserService);

    }
}
