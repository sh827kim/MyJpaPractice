# 엔티티 매핑 설정

## 기본 애노테이션

- @Entity : 엔티티 클래스에 설정, 필수
- @Table : 매핑할 테이블 지정
  - 생략 시 클래스 이름과 동일한 이름의 테이블에 매핑
  - 속성
    - name : 테이블 이름
    - catalog : 카탈로그 이름
    - schema : 스키마 이름
- @Id : 식별자 속정에 설정 ,필수
- @Column : 매핑할 컬럼명 지정
- @Enumerated : enum 타입 매핑할 때 설정
  - EnumType.STRING : enum 타입 값 이름을 저장
  - EnumType.ORDINAL : enum 타입 값의 순서를 저장 --> 값의 순서이므로 ENUM 값 순서 변동시 문제 발생.



## 접근 타입

- 필드 접근 : 필드 값을 사용하여 매핑하는 방법 
  - @Id 메서드를 필드에 붙이면 필드 접근
- 프로퍼티 접근 : getter/setter 메서드를 활용하여 매핑하는 방법
  - @Id 메서드를 getter에 붙이면 프로퍼티 접근
- @Access 애노테이션을 활용하여 명시적으로 지정하는 것도 가능.
  - 클래스/개별 필드에 적용가능
  - @Access(AccessType.PROPERTY) 또는 @Access(AccessType.FIELD)