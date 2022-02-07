package org.zoey.springboot.config.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.zoey.springboot.config.auth.dto.OAuthAttributes;
import org.zoey.springboot.config.auth.dto.SessionUser;
import org.zoey.springboot.domain.user.User;
import org.zoey.springboot.domain.user.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
// service 역할을 하는 class임을 알린다.
@Service // 비지니스 로직을 수행한다.
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;

    // session은 사전적 의미로 서버(server)와 클라이언트(client) 간에 반영구적으로 상호 작용하는 정보 교환이다.
    // session은 server로 요청(request) 하는 client를 구별하기 위해 server에 저장되는 정보이다.
    // erver resource를 사용하므로 server에 부담을 줄 수 있으며, 로드 밸런싱(load balancing) 시스템(system)에서 session 처리가 쉽지 않다
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)
        throws OAuth2AuthenticationException {
        // defaultOAuth2UserService는 OAuth2UserService의 구현체이다.
        // 해당 클래스를 이용해서 userRequest에 있는 정보를 빼낼 수 있다.
        OAuth2UserService<OAuth2UserRequest, OAuth2User>
                delegate = new DefaultOAuth2UserService();

        // user 정보를 빼낸다.
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 등록유저정보 가져오기
        // getregistrationid : 로그인 진행 중인 서비스를 구분하는 코드
        // 구글만 사용하면 불필요한 값이지만, 이후 네이버 로그인 연동시에는 네이버 로그인인지,
        // 구글 로그인인지 구분하기 위해 사용합니다.
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // userNameAttributeName
        // OAuth2 로그인 진행 시 키가 되는 필드값을 이야기한다.(Primary key)
        // 구글의 경우 기본적으로 코드지원(sub) but 네이버 카카오 등은 기본 지원하지 않는다.
        // 구글 로그인 이외 다른 로그인을 추가 진행시 사용된다.
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();


        // OAuthAttributes
        // OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스
        // 네이버 등 다른 소셜 로그인도 이 클래스 사용
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,
                oAuth2User.getAttributes());


        User user = saveOrUpdate(attributes);

        // SessionUser
        // 세션에 사용자 정보를 저장하기 위한 Dto 클래스
        // User 클래스를 쓰지않고 새로 만드는 이유 :
        httpSession.setAttribute("user", new SessionUser(user));

        // 단 한개의 객체만 저장 가능한 컬렉션 (Collections.singleton())
        // Set객체 하나만 저장
        // Collections.singleton(new TreeSet());
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
        }


        private User saveOrUpdate(OAuthAttributes attributes) {
            User user = userRepository.findByEmail(attributes.getEmail())
                    .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                    // 기존 user 가 아닌경우, User생성
                    .orElse(attributes.toEntity());

            return userRepository.save(user);
            }




}
