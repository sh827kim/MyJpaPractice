# @Embeddable

- 엔티티가 아닌 타입을 한개 이상의 필드와 매핑할 때 사용
- 엔티티의 한 속성으로 @Embeddable 적용 타입 사용



## 같은 @Embeddable 타입 필드가 두개일 때

- Repeated Column 이라는 에러 발생
- 이 경우 @AttributeOverride 라는 어노테이션을 활용하여 컬럼 명 재정의

```java
    @Embedded
    private Address homeAddress;

    @AttributeOverrides({
            @AttributeOverride(name = "address1", column = @Column(name = "waddr1")),
            @AttributeOverride(name = "address2", column = @Column(name = "waddr2")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "wzipcode"))
    })
    @Embedded
    private Address workAddress;
```

