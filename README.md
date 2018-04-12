# Spring Security Study
* Detail Explanation In Wiki

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

## CSRF 방지 기능과 스프링 MVC와의 연계
* 스프링 시큐리티에는 스프링 MVC와 연계하기 위해 만들어진 컴포넌트가 있다. 예를 들어, CsrfRequestDataValueProcessor는
스프링 MVC의 <code>\<form:form></code> 요소를 사용할 때, HTMl 폼에 CSRF 토큰 값을 hidden 항목으로 추가하는 기능을 제공한다.
* CSRF 방지 기능이 활성화되면 CsrfRequestDataValueProcessor가 스프링 MVC에 자동으로 적용되기 때문에
개발자가 명시적으로 정의를 추가해줄 필요가 없다.<br/>
HTML 폼을 만들 때 단지 <code>\<form:form></code> 요소를 사용하기만 하면 된다.

## 기본적으로 지원하는 보안 헤더
<table>
<tr>
<td>Cache-Control(Pragma, Expires)</td>
<td>
콘텐츠의 캐시 방법을 지시하기 위한 헤더다.<br/>
보호된 컨텐츠가 브라우저에 캐시되지 않게 함으로써 권한이 없는 사용자가 보호된 컨텐츠를 볼 위험을 줄일 수 있다.
</td>
</tr>
<tr>
<td>X-Frame-Options</td>
<td>
프레임에서 컨텐츠 표시를 인가할지 여부를 지시하기 위한 헤더다.<br/>
프레임에서 컨텐츠가 표시되지 않게 함으로써 클릭재킹(Clickjacking)을 통한 기밀 정보 유출을 줄일 수 있다.
</td>
</tr>
<tr>
<td>X-Content-Type-Options</td>
<td>
컨텐츠의 종류를 결정하는 방법을 지시하기 위한 헤더다.<br/>
일부 브라우저에서는 Content-Type 헤더의 값을 무시하고 컨텐츠 내용을 보고 결정한다.<br/>
컨텐츠의 종류를 결정할 때 컨텐츠의 내용을 보이지 않게 함으로써 크로스 사이트 스크립팅(XSS)을 통한 공격 위험을 줄일 수 있다.
</td>
</tr>
<tr>
<td>X-XSS-Protection</td>
<td>
브라우저의 XSS 필터를 사용해 유해 스크립트를 검출할 방법을 지시하는 헤더다.<br/>
XSS 필터를 활성화해서 유해 스크립트를 검출하게 만들면 크로스 사이트 스크립팅(XSS)을 통한 공격 위험을 줄일 수 있다.
</td>
</tr>
<tr>
<td>Strict-Transport-Security</td>
<td>
HTTPS로 접근한 후에 다시 HTTP로 접근하려 하면, HTTPS로 다시 접근하도록 지시하는 헤더다.<br/>
HTTPS로 접근한 후 HTTP가 사용되지 않게 함으로써 중간자 공격이라는 공격 수법을 통해 악의적인 사이트로 유인되는 위험을 줄일 수 있다.
</td>
</tr>
</table>