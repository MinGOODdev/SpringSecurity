# Spring Security Study

## 기본적인 보안 기능
* 인증 기능 : 애플리케이션 사용자의 정당성을 확인한다.
* 인가 기능 : 애플리케이션의 리소스나 처리에 대해 접근을 제어한다.

## 강화된 보안 기능
* 세션 관리 기능 : 세션 하이재킹(session hijacking)이나 세션 고정(session fixation) 공격으로부터 사용자를 보호 하고<br/>
세션의 라이프 라이클(생성, 파기, 타임아웃)을 제어한다.
* CSRF 방지 기능 : Cross-Site Request Forgery(CSRF) 공격으로부터 사용자를 보호한다.
* 브라우저의 보안 기능과의 연계 기능 : 브라우저의 보안 기능과 연계해서 브라우저 기능을 악용한 공격에서 사용자를 <br/>
보호할 수 있는 보안 헤더를 출력한다.

## 스프링 시큐리티의 모듈 구성
<table>
<tr>
<td>모듈명</td>
<td>설명</td>
</tr>
<tr>
<td>spring-security-core</td>
<td>인증과 인가 기능을 구현하기 위한 핵심적인 컴포넌트로 구성</td>
</tr>
<tr>
<td>spring-security-web</td>
<td>웹 애플리케이션의 보안 기능을 구현하기 위한 컴포넌트로 구성</td>
</tr>
<tr>
<td>spring-security-config</td>
<td>
각 모듈에서 제공하는 컴포넌트의 설정을 지원하기 위한 컴포넌트로 구성됨<br/>
(자바 기반 설정 방식을 지원하는 클래스나 XML 네임스페이스를 해석하는 클래스 등)
</td>
</tr>
<tr>
<td>spring-security-taglibs</td>
<td>인증 정보나 인가 정보를 사용하기 위한 JSP 태그 라이브러리로 구성</td>
</tr>
</table>

## 프레임워크 아키텍쳐
1. 클라이언트는 웹 애플리케이션에 요청을 보낸다.
2. 스프링 시큐리티의 FilterChainProxy 클래스(서블릿 필터)가 요청을 받은 다음, HttpFirewall 인터페이스의 메소드를 호출해서 HttpServletRequest와 HttpServletResponse에 대한 방화벽 기능을 수행한다.
3. FilterChainProxy 클래스는 SecurityFilterChain에 설정돼 있는 보안 필터 클래스에 처리를 위임한다. 이 필터는 실제로 서블릿 필터 형태로 만들어져 있다.
4. SecurityFilterChain에는 여러 보안 필터가 연쇄적으로 연결된 형태로 설정돼 있으며, 앞의 보안 필터가 정상적으로 처리되면 뒤이은 보안 필터가 뒤이어 호출되는 방식으로 동작한다.
5. 마지막 보안 필터의 처리가 정상적으로 종료되면 뒤이어 남은 서블릿 필터나 서블릿이 실행되어 웹 애플리케이션의 리소스에 접근할 수 있게 된다.
6. FilterChainProxy 클래스는 웹 애플리케이션에서 반환한 리소스를 클라이언트에 전달한다.

## 프레임워크에서 주요 기능을 처리하는 컴포넌트
* FilterChainProxy
FilterChainProxy 클래스는 프레임워크의 진입점 역할을 하는 서블릿 필터 클래스다. 이 클래스는 프레임워크에서 처리되는 전체 흐름을 제어하고 보안 기능과 같은 추가 기능을 필터에 위임하는 방식으로 동작한다
* HttpFirewall
HttpFirewall 인터페이스는 HttpServletRequest와 HttpServletResponse에 대한 방화벽 기능을 추가하기 위한 인터페이스다. 기본적으로 DefaultHttpFirewall 클래스가 사용되고, 디렉터리 탐색 공격이나 인가되지 않은 요청을 차단하는 역할을 한다.
* SecurityFilterChain
SecurityFilterChain 인터페이스는 FilterChainProxy가 받은 요청에 적용할 보안 필터 목록을 관리하기 위한 인터페이스다. 기본적으로 DefaultSecurityFilterChain 클래스가 사용되고 요청 패턴 별로 보안 필터 목록을 관리한다.