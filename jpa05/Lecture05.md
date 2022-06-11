# 엔티티 식별자 생성 방식

## 직접 할당

- @Id 설정 대상에 직접 값을 설정하는 방식

  - 사용자가 입력한 값, 규칙에 따라 생성한 값 등 (ex. 이메일, 주문번호...)

- 저장 하기 전 생성 시점에 생성자 할당

  

## 식별 컬럼 방식

- DB 의 식별 컬럼에 매핑 (MySQL의 자동 증가 컬럼 등)
  - DB가 식별자를 생성하므로, 객체 생성 시 식별값을 설정하지 않음
- 설정 방식
  - ```@GeneratedValue(strategy=GenerationType.IDENTITY)```
- INSERT 쿼리를 실행해야 식별자를 알 수 있음
  - EntityMananger.persist() 호출 시점에 INSERT 쿼리 실행
  - persist() 실행 시 객체에 식별자 값 할당됨.

```java
Order order = new Order("chicken", "Gangnam-Seoul", LocalDateTime.now());
// Insert 쿼리 실행 (commit 전 실행. 그래야 식별자를 얻을 수 있음.)
entityManager.persist(order);
// persist 이후 식별자 사용 가능.
Long generatedId = order.getId(); 
```





## 시퀀스 사용 방식

- 시퀀스를 사용해서 식별자 생성
  - JPA가 식별자 생성 처리 -> 객체 생성 시에 식별자를 설정하지 않음
- 설정 방식
  - @SequenceGenerator 로 시퀀스 생성기 설정
  - @GeneratedValue의 generator로 시퀀스 생성기 지정
- EntityMananger.persist() 호출 시점에 시퀀스 사용
  - persist() 실행 시 객체에 식별자 값 할당됨.
  - Insert 쿼리는 실행하지 않음.

```java
Order order = new Order("chicken", "Gangnam-Seoul", LocalDateTime.now());
// 시퀀스로 식별자를 구함
entityManager.persist(order);
// 커밋 시점에 insert 쿼리 실행
transaction.commit();
```





## 테이블 사용 방식

- 테이블을 시퀀스처럼 사용
  - 테이블에 엔티티를 위한 키를 보관함
  - 해당 테이블을 활용하여 다음 식별자 생성 (select tbl.nextval from id_seq tbl where tbl.entity=? for update )
- 설정 방식
  - @TableGenerator 로 테이블 생성기 설정
  - @GeneratedValue의 generator 로 테이블 생성기 지정
- EntityManager.persist() 호출 시점에 테이블 사용
  - persist() 실행 시 테이블을 이용해서 식별자를 구하고 엔티티에 식별자 값 할당.
  - Insert 쿼리는 실행하지 않음.