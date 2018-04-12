package spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import spring.security.domain.User;

import java.util.ArrayList;
import java.util.List;

/*
MyAuthenticationProvider 클래스는
사용자가 입력한 로그인 아이디와 비밀번호를 검사할 때 사용되는 클래스이다.
 */

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    public Authentication authenticate(String loginId, String password) throws AuthenticationException {
        // 사용자가 입력한 아이디와 비밀번호를 검사한다.
        User user = userService.login(loginId, password);
        if(user == null) return null;

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String role = "";
        switch(user.getUserType()) {
            case "관리자": role = "ROLE_ADMIN"; break;
        }
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        return new MyAuthentication(loginId, password, grantedAuthorities, user);
    }

    /*
    사용자가 입력한 로그인 아이디와 비밀번호를 검사할 때,
    스프링 시큐리티 엔진에 의해 이 클래스의 authenticate 메소드가 자동 호출된다.
    사용자가 입력한 로그인 아이디와 비밀번호가 이 메소드의 파라미터로 전달된다.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginId = authentication.getName();
        String passwd = authentication.getCredentials().toString();
        return authenticate(loginId, passwd);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public class MyAuthentication extends UsernamePasswordAuthenticationToken {
        private static final long serialVersionUID = 1L;
        User user;

        public MyAuthentication(String loginId, String passwd, List<GrantedAuthority> grantedAuthorities, User user) {
            super(loginId, passwd, grantedAuthorities);
            this.user = user;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }
}
