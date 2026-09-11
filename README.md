<div align="center">

# あつまり · ATSUMARI

### 日本の祭りに、出会う旅。

일본 전국의 축제를 지역과 계절, 키워드로 발견하고 정보를 나누는 축제 안내 웹 서비스

`Java 21` · `Spring Boot 4.0.8` · `JSP` · `MySQL` · `Gradle 9.5.1`

</div>

![아츠마리 메인 화면](docs/images/home-hero.jpg)

## 프로젝트 소개

아츠마리(あつまり)는 일본 전국에서 열리는 축제를 한곳에 모아 사용자가 원하는 축제를 쉽고 빠르게 찾을 수 있도록 만들었습니다.

사용자는 축제명과 날짜로 검색하거나 지역·도도부현·계절을 기준으로 축제를 탐색할 수 있습니다. 축제의 일정, 장소, 교통, 요금 등 상세 정보를 확인하고 커뮤니티를 통해 경험과 정보를 공유할 수 있습니다.

## 팀 역할

| 팀원 | 담당 영역 | 주요 작업 |
| --- | --- | --- |
| **Hong Seok-min (`seokmin`) · 팀장** | 프로젝트 기반·공통 | 프로젝트 관리, 초기 구조, DB 설정, 홈, 공통 UI, 마이페이지, 보안 설정 |
| `KimGyeonkang` | 회원 | 회원가입, 이메일 중복 확인, 로그인·로그아웃, 세션, 회원 DAO·Service |
| 김태현 | 축제 조회 | 축제 목록·상세·카드, 지역별 화면, 검색·페이지 처리, 축제 UI |
| `Gyongee` | 축제 검색·문의 | 외부 축제 데이터, 지역·검색 로직, 문의 기능 및 관리자 화면 |
| `ohy1027` | 커뮤니티 | 게시글 목록·상세·글쓰기·등록, 커뮤니티 UI |

> 역할 표의 이름은 현재 Git 작성자 기록을 기준으로 작성했습니다.

## 주요 기능

| 영역 | 기능 |
| --- | --- |
| 축제 | 축제 목록·카드·상세 조회, 키워드 검색, 페이지 이동 |
| 탐색 | 날짜, 계절, 지역 및 도도부현 기준 탐색 |
| 회원 | 회원가입, 이메일 중복 확인, 로그인·로그아웃, 세션 관리 |
| 마이페이지 | 회원 정보 조회 및 수정 화면 |
| 커뮤니티 | 게시글 목록·상세·작성 및 등록 |
| 문의 | 문의 목록·상세·작성·수정, 관리자 문의 화면 |

## 자세히 보기

화면별 스크린샷, 프로젝트 개요, 기술 스택과 개발 문서는 아래 Notion에서 확인할 수 있습니다.

### [아츠마리 프로젝트 Notion 바로가기](https://app.notion.com/p/3b6f3c769d45802bb464d31c80fe8b8d)

## 기술 스택

| 구분 | 기술 |
| --- | --- |
| Frontend | JSP, JSTL, HTML5, CSS3, JavaScript, jQuery |
| Backend | Java 21, Spring Boot 4.0.8, Spring MVC, Jakarta Servlet |
| Database | MySQL, JDBC, Spring JDBC |
| Security | Spring Security, BCrypt |
| Data | Jackson Databind, Spring Data JPA/Hibernate 의존성 |
| Build | Gradle 9.5.1, WAR |
| View Engine | Tomcat Jasper, Jakarta JSTL |

> 현재 주요 DAO는 SQL을 직접 실행하는 JDBC 방식으로 구현되어 있습니다. Spring Data JPA 의존성은 향후 확장을 위해 포함되어 있습니다.

## 프로젝트 구조

```text
src/main
├── java/org/example/atumari
│   ├── common       # 보안 설정, DB 연결 및 공통 기능
│   ├── festival     # 축제 조회·검색·외부 데이터 처리
│   ├── member       # 회원가입·로그인·마이페이지
│   ├── community    # 커뮤니티 게시글
│   ├── inquiry      # 사용자 및 관리자 문의
│   └── home         # 메인 화면
├── resources
│   ├── application.yaml
│   └── db/database.sql
└── webapp
    ├── WEB-INF/views
    └── assets
```

## 시작하기

### 준비 사항

- Java 21
- MySQL
- Git

Gradle은 Wrapper를 사용하므로 별도로 설치하지 않아도 됩니다.

### 1. 저장소 복제

```bash
git clone git@github.com:seokmingg/atumari.git
cd atumari
```

### 2. 환경 변수 설정

```bash
cp .env.example .env
```

생성된 `.env`에서 자신의 MySQL 접속 정보를 입력합니다.

```properties
DB_SERVER_URL=jdbc:mysql://localhost:3306/
DB_URL=jdbc:mysql://localhost:3306/atumari?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul
DB_USERNAME=root
DB_PASSWORD=your-password
SERVER_PORT=8080
```

> `.env`에는 비밀번호가 포함되므로 Git에 커밋하지 않습니다.

### 3. 실행

MySQL 서버를 실행한 뒤 다음 명령을 입력합니다.

```bash
./gradlew bootRun
```

애플리케이션 시작 시 `database.sql`을 통해 `atumari` 데이터베이스와 기본 테이블이 준비됩니다.

브라우저에서 [http://localhost:8080](http://localhost:8080)에 접속합니다.

## 개발 현황

현재 팀 프로젝트로 기능을 단계적으로 구현하고 있습니다. 화면과 기능이 변경되면 README의 설명과 스크린샷도 함께 갱신합니다.

---

<div align="center">

**あつまり — 全国各地の祭りから、あなたの特別な一日を見つけよう。**

</div>
