# Fruit Frenzy - Spring Boot Server

애니팡 스타일 싱글 플레이 게임의 서버입니다.  
회원가입, 로그인(JWT), 점수 저장, Top10 랭킹 API를 제공합니다.

---

## 기술 스택
- Java 17
- Spring Boot
- Spring Security + JWT
- Spring Data JPA
- MySQL
- Maven

---

## 주요 기능
- 회원가입 / 로그인 (JWT 발급)
- 인증 기반 점수 제출
- 최고 점수만 저장
- Top10 랭킹 조회

---

## 실행 방법

### 1. MySQL 실행
```sql
CREATE DATABASE match3;
