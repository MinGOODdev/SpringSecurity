package spring.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
@EnableWebSecurity
: 스프링 시큐리티가 제공하는 설정 클래스가 import되고 스프링 시큐리티를 이용할 때 필요한 컴포넌트 빈이 자동으로 정의됨

WebSecurityConfigurerAdapter
: 위 클래스를 상속하면 기본적으로 적용되는 빈의 정의를 간단히 커스터마이징할 수 있다.
 */

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**");
        // 보안 기능이 필요없는 리소스(CSS, JS)에는 스프링 시큐리티를 적용하지 않는다.
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