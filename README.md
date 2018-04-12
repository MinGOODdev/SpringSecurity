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