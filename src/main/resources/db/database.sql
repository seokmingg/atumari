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

-- 도도부현 기본 데이터
-- 같은 prefecture_no가 이미 존재하면 해당 행은 건너뜁니다.
INSERT IGNORE INTO prefecture
    (prefecture_no, prefecture_name, region_name)
VALUES
    (1,  '北海道', '北海道'),
    (2,  '青森県', '東北'),
    (3,  '岩手県', '東北'),
    (4,  '宮城県', '東北'),
    (5,  '秋田県', '東北'),
    (6,  '山形県', '東北'),
    (7,  '福島県', '東北'),
    (8,  '茨城県', '関東'),
    (9,  '栃木県', '関東'),
    (10, '群馬県', '関東'),
    (11, '埼玉県', '関東'),
    (12, '千葉県', '関東'),
    (13, '東京都', '関東'),
    (14, '神奈川県', '関東'),
    (15, '新潟県', '中部'),
    (16, '富山県', '中部'),
    (17, '石川県', '中部'),
    (18, '福井県', '中部'),
    (19, '山梨県', '中部'),
    (20, '長野県', '中部'),
    (21, '岐阜県', '中部'),
    (22, '静岡県', '中部'),
    (23, '愛知県', '中部'),
    (24, '三重県', '近畿'),
    (25, '滋賀県', '近畿'),
    (26, '京都府', '近畿'),
    (27, '大阪府', '近畿'),
    (28, '兵庫県', '近畿'),
    (29, '奈良県', '近畿'),
    (30, '和歌山県', '近畿'),
    (31, '鳥取県', '中国'),
    (32, '島根県', '中国'),
    (33, '岡山県', '中国'),
    (34, '広島県', '中国'),
    (35, '山口県', '中国'),
    (36, '徳島県', '四国'),
    (37, '香川県', '四国'),
    (38, '愛媛県', '四国'),
    (39, '高知県', '四国'),
    (40, '福岡県', '九州'),
    (41, '佐賀県', '九州'),
    (42, '長崎県', '九州'),
    (43, '熊本県', '九州'),
    (44, '大分県', '九州'),
    (45, '宮崎県', '九州'),
    (46, '鹿児島県', '九州'),
    (47, '沖縄県', '九州');

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
