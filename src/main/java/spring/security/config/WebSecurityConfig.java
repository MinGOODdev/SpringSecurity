package spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import spring.security.service.MyAuthenticationProvider;

/*
@EnableWebSecurity
: 스프링 시큐리티가 제공하는 설정 클래스가 import되고 스프링 시큐리티를 이용할 때 필요한 컴포넌트 빈이 자동으로 정의됨

WebSecurityConfigurerAdapter
: 위 클래스를 상속하면 기본적으로 적용되는 빈의 정의를 간단히 커스터마이징할 수 있다.
 */

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationProvider myAuthenticationProvider;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/res/**");
        // 보안 기능이 필요없는 리소스(*.css, *.js, *.png 등)에는 스프링 시큐리티를 적용하지 않는다.
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests()        // 권한 설정 시작 (앞의 설정이 뒤의 설정보다 우선 순위가 높다.)
                    // /admin/** 패턴의 URL은 ROLE_ADMIN 권한을 소유한 사용자만 요청할 수 있다.
                    .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                    // /guest/** 패턴의 URL은 모든 사용자에게 허용된다.
                    .antMatchers("/guest/**").permitAll()
                    // / URL은 모든 사용자에게 허용된다.
                    .antMatchers("/**").permitAll()
                    // /** 패턴의 URL은 로그인 된 사용자에게만 허용된다.
                    .antMatchers("/**").authenticated();

//        httpSecurity.csrf().disable();

        httpSecurity.formLogin()
                .loginPage("/guest/login")                      // 로그인 페이지 설정
                .loginProcessingUrl("/guest/login_processing")  // 로그인 절차는 스프링 시큐리티 엔진에 의해 자동으로 진행
                .failureUrl("/guest/login?error")
                .defaultSuccessUrl("/user/index", true)
                // 로그인 페이지(뷰)에서 아이디와 비밀번호 input 태그의 name 값 설정
                .usernameParameter("loginId")
                .passwordParameter("passwd");

        httpSecurity.logout()                       // 로그아웃 설정
                    .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout_processing"))
                    .logoutSuccessUrl("/guest/login")
                    .invalidateHttpSession(true);   // 로그아웃할 때, 세션에 들어있는 데이터를 전부 지우라는 설정

        httpSecurity.authenticationProvider(myAuthenticationProvider);
    }


    // 아래와 같이 빈을 정의하면 지정한 경로 패턴마다 다른 보안 기능을 적용할 수 있다.
    @Configuration
    @Order(1)
    public static class UiWebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.antMatcher("/ui/**");
        }
    }

    @Configuration
    @Order(2)
    public static class ApiWebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.antMatcher("/api/**");
        }
    }
}