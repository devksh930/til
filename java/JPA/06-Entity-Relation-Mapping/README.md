# 다양한 연관관계 매핑

## 다대일

- 다대일 관계의 반대방향은 항상 일대다 관계이고 일대다의 반대 방향은 항상 다대일 관계이다.
- 테이블의 일(1), 다(N)관계에서 외래 키는 항상 다쪽에 있다.
- 객체의 양방향 관계에서 연관관계의 주인은 항상 다 쪽이다.
    - 예를 들어 회원(N),팀(1) 이 있으면 회원이 연관관계의 주인이다.

### 다대일 단방향[N:1]
![다대일 단방향](https://user-images.githubusercontent.com/45715241/115025593-84175180-9efc-11eb-81a9-5ae5bf75bda8.png)

- 회원은 Member.team으로 팀 엔티티를 참조할 수 있지만 반대로 팀에는 회원을 참조하는 필드가 없다. 따라서 회원과 팀은 다대일 단방향 연관관계

```java
@ManyToOne
@JoinColumn(name = "TEAM_ID")
private Team team;
```

- `@JoinColumn(name = "Team_ID")`을 사용해서 `Member.team` 필드로 회원 테이블의 `TEAM_ID`외래키를 관리한다.
