# SPRINGBOOT-REDIS
- REDIS(REmote Dictionary Server)는 메모리 기반의 “키-값” 구조 데이터 관리 시스템이며, 모든 데이터를 메모리에 저장하고 조회하기에 빠른 Read, Write 속도를 보장하는 비 관계형 데이터베이스이다.

### 스프링 데이터 redis를 사용 하는 방법
- StringRedis Template 또는 RedisTemplate : String에 특화
- CrudRepository : jpa 레포지터리 사용하듯이 사용가능

### Redis 주요 커맨드
- keys *
- get {key}
- hgetall {key} (hash값)
- hget {key} {column} (hash값)

### 커스터마이징
- spring.redis.*