<div align="center">

# あつまり · ATSUMARI

### 日本の祭りに、出会う旅。

日本全国の祭りを地域・季節・キーワードから探し、情報を共有できる祭り案内Webサービス

`Java 21` · `Spring Boot 4.0.8` · `JSP` · `MySQL` · `Gradle 9.5.1`

</div>

![あつまりのメイン画面](docs/images/home-hero.jpg)

## プロジェクト紹介

「あつまり」は、日本全国で開催される祭りの情報を一か所に集め、ユーザーが興味のある祭りを簡単に見つけられるようにすることを目的として開発しました。

祭りの名称や日付による検索に加え、地域・都道府県・季節から祭りを探すことができます。開催期間、会場、アクセス、料金などの詳細情報を確認し、コミュニティを通して祭りの体験や情報を共有できます。

## チーム構成・担当

| メンバー                                    | 担当領域 | 主な担当機能 |
|---------------------------------------------| --- | --- |
| ホン・ソクミン (`seokmin`) · チームリーダー | プロジェクト基盤・共通 | プロジェクトの初期構成、データベース接続・初期化、Spring Securityの初期設定 |
| キム・ギョンガン (`KimGyeonkang`)           | 会員 | 会員登録、ログイン、ログアウト、セッション処理、マイページ |
| キム・テヒョン (`Kanewesto`)                | 祭り情報の表示 | ホーム・共通機能、全体的なUI設計・実装、祭り一覧・詳細、地域・検索・ページング処理 |
| ソ・ギョン (`Gyongee`)                      | 祭りAPI連携・お問い合わせ | 外部祭りAPIとの連携、データの整形・DB保存機能の実装、お問い合わせCRUD、管理者用お問い合わせ画面 |
| オ・ハヨン (`ohy1027`)                      | コミュニティ | コミュニティ一覧・詳細・投稿作成、投稿登録処理、コミュニティ画面のUI |

> メンバー名は、現在のGitコミット履歴に記録されている作成者名を基準にしています。

## 主な機能

| 分類 | 機能 |
| --- | --- |
| 祭り | 祭りの一覧・カード・詳細表示、キーワード検索、ページング |
| 検索 | 日付、季節、地域、都道府県を基準とした祭り検索 |
| 会員 | 会員登録、メールアドレス重複確認、ログイン・ログアウト、セッション管理 |
| マイページ | 会員情報の確認・編集画面 |
| コミュニティ | 投稿一覧・詳細・作成・登録 |
| お問い合わせ | お問い合わせ一覧・詳細・作成・編集、管理者用画面 |

## 詳細情報

画面別のスクリーンショット、プロジェクト概要、技術スタック、開発資料は、以下のNotionで確認できます。

### [あつまりプロジェクトのNotionを見る](https://app.notion.com/p/3b6f3c769d45802bb464d31c80fe8b8d)

## 技術スタック

| 分類 | 技術 |
| --- | --- |
| Frontend | JSP, JSTL, HTML5, CSS3, JavaScript, jQuery |
| Backend | Java 21, Spring Boot 4.0.8, Spring MVC, Jakarta Servlet |
| Database | MySQL, JDBC, Spring JDBC |
| Security | Spring Security, BCrypt |
| Data | Jackson Databind, Spring Data JPA / Hibernate |
| Build | Gradle 9.5.1, WAR |
| View Engine | Tomcat Jasper, Jakarta JSTL |

> 現在、主要なDAOはSQLを直接実行するJDBC方式で実装しています。Spring Data JPAの依存関係は、今後の拡張に備えて導入しています。

## データベース設計

会員、祭り、コミュニティ、お問い合わせ機能で使用するテーブル構成とリレーションは、ERDCloudで確認できます。

### [あつまりのERDを見る](https://www.erdcloud.com/d/okiHje2oA8gQgmZp3)

## プロジェクト構成

```text
src/main
├── java/org/example/atumari
│   ├── common       # セキュリティ設定、DB接続、共通機能
│   ├── festival     # 祭りの表示・検索・外部データ処理
│   ├── member       # 会員登録・ログイン・マイページ
│   ├── community    # コミュニティ投稿
│   ├── inquiry      # ユーザー・管理者向けお問い合わせ
│   └── home         # メイン画面
├── resources
│   ├── application.yaml
│   └── db/database.sql
└── webapp
    ├── WEB-INF/views
    └── assets
```

## 実行方法

### 必要な環境

- Java 21
- MySQL
- Git

Gradle Wrapperを使用するため、Gradleを別途インストールする必要はありません。

### 1. リポジトリをクローン

```bash
git clone git@github.com:seokmingg/atumari.git
cd atumari
```

### 2. 環境変数を設定

```bash
cp .env.example .env
```

作成された`.env`ファイルに、自分のMySQL接続情報を入力します。

```properties
DB_SERVER_URL=jdbc:mysql://localhost:3306/
DB_URL=jdbc:mysql://localhost:3306/atumari?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul
DB_USERNAME=root
DB_PASSWORD=your-password
SERVER_PORT=8080
```

> `.env`にはデータベースのパスワードが含まれるため、Gitにコミットしないでください。

### 3. アプリケーションを起動

MySQLサーバーを起動した後、次のコマンドを実行します。

```bash
./gradlew bootRun
```

アプリケーションの起動時に`database.sql`が実行され、`atumari`データベースと基本テーブルが準備されます。

ブラウザで[http://localhost:8080](http://localhost:8080)にアクセスします。

## 開発状況

現在、チームプロジェクトとして各機能を段階的に実装しています。画面や機能に変更があった場合は、READMEの説明とスクリーンショットもあわせて更新します。

---

<div align="center">

**あつまり — 全国各地の祭りから、あなたの特別な一日を見つけよう。**

</div>
