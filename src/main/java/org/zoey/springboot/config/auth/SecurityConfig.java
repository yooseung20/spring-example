package org.zoey.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.zoey.springboot.domain.user.Role;


@RequiredArgsConstructor

// Spring Security 설정들을 활성화 시켜준다.
@EnableWebSecurity

// 사용자 지정 자체 보안 구성을 원할 때, WebSecurityConfigurerAdapter 을 상속받아 구현하면 된다.
// 이렇게 하면 기본 자동 구성이 비활성화되고 사용자 지정 보안 구성이 활성화 된다
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // h2-console 화면을 사용하기 위해 옵션들을 disable 합니다.
        http.csrf().disable().headers().frameOptions().disable()
                .and()
                // URL별 권한 관리를 설정하는 옵션의 시작점 이다.
                // authorizeRequests가 선언되어야만 antMatchers 옵션을 사용할 수 있다.
                .authorizeRequests()
                // antMatchers 설정한 리소스의 접근에 인증절차 없이 허용한다.
                .antMatchers("/", "/css/**", "/images/**", "/js/**","h2-console/**").permitAll()
                // 'api/vi/**' 주소를 가진 API는 User권한을 가진 사람만 가능하도록 한다.
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // anyRequest: 설정된 값들 이외 나머지 url
                // authenticated() : 나머지 url들은 모두 인증된 사용자들에게만 허용한다.
                // 인증된 사용자 즉, 로그인한 사용자들을 이야기한다.(User 및 Guest)
                .anyRequest().authenticated()
                .and()
                // 로그아웃 기능에 대한 여러 설정의 진입점
                // 로그아웃 성공시 "/" 주소로 이동합니다.
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login() //oauth2Login OAuth2 로그인 기능에 대한 여러 설정의 진입점
                .userInfoEndpoint() // 로그인 성공 이후 사용자 정보를 가져올 떄의 설정들을 담당
                // userService : 소셜 로그인 성공시 후속 조치를 진행할 UserService인터페이스의 구현체 등록
                // 리소스 서버(즉,소셜서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고 자하는 기능 명시
                .userService(customOAuth2UserService);
    }

}
