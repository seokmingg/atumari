-- 아츠마리 데이터베이스 생성
-- 이미 존재하면 새로 만들지 않으므로 여러 번 실행해도 됩니다.
CREATE DATABASE IF NOT EXISTS atumari
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- 이후 작성하는 테이블은 atumari 데이터베이스에 생성됩니다.
USE atumari;

-- 회원 기본 정보
CREATE TABLE IF NOT EXISTS member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- 회원 구분 번호
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    nickname VARCHAR(255),
    tel VARCHAR(100), -- 010-0000-0000
    reg_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modify_date TIMESTAMP,
    exit_date TIMESTAMP,
    rk VARCHAR(100), -- 회원 등급(rank): 매니저, 기자, 일반 등
    filepath VARCHAR(5000) -- 추후 프로필 사진 등 첨부파일 구현 시 파일 경로
);

-- 회원 인증 정보
CREATE TABLE IF NOT EXISTS member_auth (
    auth_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- 인증 구분 번호
    member_id BIGINT NOT NULL, -- 회원 구분 번호
    password VARCHAR(255),
    provider VARCHAR(20) NOT NULL, -- LOCAL, GOOGLE, NAVER, KAKAO 등
    provider_id VARCHAR(255), -- 외부 인증 제공자가 발급한 ID
    UNIQUE (provider, provider_id),
    UNIQUE (member_id, provider),
    CONSTRAINT FK_MEMBER_AUTH
        FOREIGN KEY (member_id)
        REFERENCES member(id)
        ON DELETE CASCADE, -- 회원 정보 삭제 시 인증 정보도 삭제
    CONSTRAINT CK_MEMBER_AUTH
        CHECK (
            (provider = 'LOCAL'
                AND password IS NOT NULL
                AND provider_id IS NULL)
            OR
            (provider <> 'LOCAL'
                AND password IS NULL
                AND provider_id IS NOT NULL)
        )
);

-- 일본 도도부현 정보
CREATE TABLE IF NOT EXISTS prefecture (
    prefecture_no INT PRIMARY KEY,
    prefecture_name VARCHAR(100) NOT NULL,
    region_name VARCHAR(100) NOT NULL
);

-- 축제 정보
CREATE TABLE IF NOT EXISTS festival (
    festival_no INT AUTO_INCREMENT PRIMARY KEY,
    festival_id INT NOT NULL UNIQUE,
    festival_name VARCHAR(200) NOT NULL,
    summary TEXT,
    start_datetime DATETIME,
    end_datetime DATETIME,
    venue_name VARCHAR(300),
    venue_address VARCHAR(300),
    access_info TEXT,
    image_url TEXT,
    organizer VARCHAR(300),
    price_free BOOLEAN,
    price_text VARCHAR(500),
    external_url TEXT,
    image_source TEXT,
    prefecture_no INT,
    season VARCHAR(20),
    CONSTRAINT FK_FESTIVAL_PREFECTURE
        FOREIGN KEY (prefecture_no)
        REFERENCES prefecture(prefecture_no)
);
