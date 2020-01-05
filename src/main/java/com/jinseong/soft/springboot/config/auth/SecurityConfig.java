package com.jinseong.soft.springboot.config.auth;

import com.jinseong.soft.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuthUserService customOAuthUserService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //h2-console 화면을 사용하기 위해 해당 옵션들을 disable함
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                //URL별 권한 관리를 설정하는 옵션의 시작점
                .authorizeRequests()
                //권한 관리 대상을 지정하는 옵션
                //"/"로 지정된 URL들은 permitAll() 옵션을 통해서 전체 열람 권한을 줌
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                //POST 메소드 이면서 "/api/v1/**/" 주소를 가진 APIsms USER 권한을 가진 사람만 가능하게 함
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                //설정된 값들 이외 나머지 URL들을 나타냄
                //여기서는 authenticated()을 추가하여 나머지 URL들은 모두 인증된 사용자들에게만 허용하게 함
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                //OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                .userInfoEndpoint()
                //소셜 로그인 성공 시 후속 조치를 진핼할 UserService 인터페이스의 구현체를 등록
                .userService(customOAuthUserService);


    }
}
