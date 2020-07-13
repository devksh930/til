# SpEL (SPRING  Expression Language)

## 스프링 EL

- 객체 그래프를 조회하고 조작하는 기능
- 스프링3.0부터 지원
- Unified EL(JSP)와 비슷하지만 메소드호출, 문자열템플릿기능 제공
- 자바에서 사용할수 있는 여러 EL이 있지만 spEL은 모든 스프링 프로젝트 전반에 걸쳐 사용가능

## 문법

- #{“표현식”}
- ${“프로퍼티”}
- 같이도 사용가능하지만 표현식안에는 프로퍼티를 사용가능 하지만 반대의 경우엔 불가능
> - #{${my.data}+1}
  - 추가적인 건 [레퍼런스](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#expressions-language-ref) 참고

## 어디스 쓰나?
- @Value 에노테이션
- @ConditionalOnExpression 애노테이션 (선택적으로 빈을 등록 )
- 스프링 시큐리티
- 스프링 데이터(쿼리의 파리미터)
