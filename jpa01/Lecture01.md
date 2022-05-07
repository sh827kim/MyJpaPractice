# JPA 01
**[참고] JPA 3.0 Spec : https://jakarta.ee/specifications/persistence/3.0/jakarta-persistence-spec-3.0.html**



## JPA의 정의

- ORM (Object-Relational Mapping) Spec.
  - Java 객체와 관계형 DB 간의 Mapping 처리를 위한 API
- Java Persistence API (version 2.2)
- Jakarta Persistence API (version 3.0)
  - 2.2 버전부터 JCP -> Eclipse 재단으로 이관
  - Jakarta EE 9 버전 : JPA 3.0
- 스프링 6부터 Jakarta EE 9+ 지원

## JPA의 특징
- 애노테이션을 활용한 매핑 설정 (xml 활용한 매핑 설정도 가능)
- String, int, LocalDate 등 기본 타입에 대한 매핑 지원
- 커스텀 타입 변환기 지원
  - User, Money 등 의 커스텀 타입에 대해 DB  column에 매핑 가능
- 밸류 타입 매핑 지원
  - 한개 이상의 column을 한 개의 타입으로 매핑 가능
- 클래스 간 연관 관계 지원 : 1-1, 1-N, N-1, N-M
- 상속에 대한 매핑 지원

## Hibernate

- JPA Spec에 대한 구현체

### 주요 개념

**Entity**

- DB 테이블과 매칭이 되는 개념
- 테이블은 하나이지만 경우에 따라 엔티티는 여러개를 만들 수 있음. 

**EntityManager**
- Entity를 관리하는 역할을 수행하는 클래스
- EntityManager 내부에 PersistenceContext라는 것을 두어 Entity들을 관리함

**Transaction**
- 하나의 DB 작업 단위
- Transaction이 Commit 되기 전까지 모든 쿼리문은 Persistence Context 내부의 쓰기 지연 SQL 저장소에 저장됨.
- Transaction이 Commit이 되는 순간 지연 저장소의 쿼리들이 실행됨.
- Rollback이 필요한 경우 아예 쿼리 실행자체를 하지 않음.

**EntityManagerFactory**
- EntityManager의 동시성 이슈를 방지하기 위해 EntityManager를 생성해주는 Factory
  - EntityManager는 여러 스레드가 동시 접근하면 동시성 이슈가 발생하므로, 스레드 간에 공유가 이루어져서는 안됨.
    - 데이터 ACID 보장의 문제. 다른 스레드로 인해 데이터가 변질 되는 경우를 막아야 함.
  - EntityManager는 필요할 때마다 계속해서 만들어줘야 함.
- EntityManager와 달리 여러 스레드가 동시에 접근해도 안전함. 단순히 EntityManager를 생성하는 역할만 하기 때문.
- 생성에 필요한 자원적 비용이 비교적 큼
  - EntityManagerFactory의 경우 Singleton 인스턴스 형태로 DB 당 하나만 사용하는 것이 좋음.

## 실습을 통해 알 수 있는 점

1. 간단한 설정으로 클래스와 테이블간 매핑 처리가 가능하다.
2. EntityManager를 이용해서 DB 연동처리를 할 수 있다.
3. 객체 변경만으로도 DB 테이블의 내용을 업데이트 할 수 있다.
4. 쿼리 작성이 필요 없다.