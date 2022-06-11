# 엔티티 단위 CRUD 처리

- Create : persist()
- Read : find()
- Update :  트랜잭션 내에서 엔티티 변경시 자동으로 변경 반영됨. (merge()는 별도로)
- Delete : remove()
  - find 로 찾은 객체에 대해서만 remove가 가능. 그게 아니면 remove 불가.