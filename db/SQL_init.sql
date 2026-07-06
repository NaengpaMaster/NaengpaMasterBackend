-- =========================================================
-- SQL_init.sql
-- 실제 운영 DB(naengpa_db, PostgreSQL 18.4)의 pg_dump(2026-07-06)를
-- 기준으로 다시 정리한 스키마 전용(DDL-only) 초기화 스크립트.
--
-- init.sql과의 차이(운영 DB 기준으로 정정/추가된 부분):
--   - members: is_deleted 컬럼 제거(운영 DB에 없음), status/maintenance_period 추가,
--              password 길이 255 -> 1000
--   - product_categories.name 길이 100 -> 255
--   - shopping_items.updated_at / deleted_at 타입 TIMESTAMP -> DATE
--   - notifications: target_type CHECK 제약 추가, 인덱스 2종 추가
--   - refresh_tokens.refresh_token UNIQUE 제약 추가
--   - recipes: food_category_id 컬럼 추가(아래 TODO 참고 - 고아 컬럼)
--   - FK(외래키) 제약조건 7건 추가(운영 DB에 실제로 존재하는 것만)
--
-- 알려진 드리프트(TODO - 별도 확인/정리 필요, 아래에서는 운영 DB 그대로 반영):
--   1) recipes.food_category_id: 과거 Recipe Entity에 있던 필드가
--      recipe_food_categories 매핑 테이블로 리팩토링되며 Entity에서는
--      제거됐지만 운영 DB 컬럼+FK는 남아있음(고아 컬럼). 실제 사용 여부
--      확인 후 컬럼 자체를 DROP할지 결정 필요.
--   2) recipe_food_categories: "레시피당 대표 카테고리 1개만 허용" 하려면
--      UNIQUE(recipe_id) 제약이 있어야 하는데, 운영 DB에는 이 제약이
--      반영되어 있지 않다(PK만 존재). 아래 스크립트는 운영 DB 실제 상태를
--      그대로 반영해 UNIQUE를 걸지 않았다 - 필요 시 마이그레이션으로 추가할 것.
--   3) member_avoid_products / member_favorite_foods: 운영 DB에는 동일 컬럼
--      조합에 이름만 다른 UNIQUE 제약이 2개씩 중복 존재한다. 아래 스크립트는
--      깨끗한 제약 1개만 선언했다 - 운영 DB에서는 중복분 정리(DROP)를 검토할 것.
-- =========================================================

-- =========================================
-- 회원
-- =========================================
CREATE TABLE members (
                         member_id BIGSERIAL PRIMARY KEY,

                         email VARCHAR(100) NOT NULL,
                         password VARCHAR(1000) NOT NULL,
                         nickname VARCHAR(50) NOT NULL,

                         role VARCHAR(20) NOT NULL
                             CHECK (role IN ('USER', 'ADMIN'))
                                                       DEFAULT 'USER',

                         status VARCHAR(20) NOT NULL
                             CHECK (status IN ('ACTIVE', 'INACTIVE')),

                         household_type VARCHAR(20) NOT NULL,

                         maintenance_period INTEGER NOT NULL DEFAULT 0,

                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP,
                         deleted_at TIMESTAMP,

                         CONSTRAINT uk_members_email UNIQUE(email)
);

COMMENT ON TABLE members IS '회원';

-- =========================================
-- 냉장고 재료
-- =========================================
CREATE TABLE fridge_items (
                              fridge_item_id BIGSERIAL PRIMARY KEY,

                              member_id BIGINT NOT NULL,
                              product_id BIGINT NOT NULL,

                              quantity VARCHAR(100) NOT NULL,
                              expiry_date DATE,

                              memo VARCHAR(1000),

                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP,

                              is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
                              deleted_at TIMESTAMP
);

COMMENT ON TABLE fridge_items IS '냉장고 보관 재료';

-- =========================================
-- 장보기 목록
-- =========================================
CREATE TABLE shopping_items (
                                shopping_item_id BIGSERIAL PRIMARY KEY,

                                member_id BIGINT NOT NULL,
                                product_id BIGINT NOT NULL,

                                quantity VARCHAR(100),

                                is_purchased BOOLEAN NOT NULL DEFAULT FALSE,

                                created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                updated_at DATE,

                                is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
                                deleted_at DATE
);

COMMENT ON TABLE shopping_items IS '장보기 목록';

-- =========================================
-- 레시피 즐겨찾기
-- =========================================
CREATE TABLE recipe_favorites (
                                  recipe_favorite_id BIGSERIAL PRIMARY KEY,

                                  recipe_id BIGINT NOT NULL,
                                  member_id BIGINT NOT NULL,

                                  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                  CONSTRAINT uk_recipe_favorites
                                      UNIQUE(recipe_id, member_id)
);

COMMENT ON TABLE recipe_favorites IS '레시피 즐겨찾기';

-- =========================================
-- 레시피 댓글
-- =========================================
CREATE TABLE recipe_comments (
                                 recipe_comment_id BIGSERIAL PRIMARY KEY,

                                 member_id BIGINT NOT NULL,
                                 recipe_id BIGINT NOT NULL,

                                 content VARCHAR(1000) NOT NULL,

                                 created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 updated_at TIMESTAMP,

                                 is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
                                 deleted_at TIMESTAMP
);

COMMENT ON TABLE recipe_comments IS '레시피 댓글';

-- =========================================
-- 문의
-- =========================================
CREATE TABLE inquiries (
                           inquiry_id BIGSERIAL PRIMARY KEY,

                           member_id BIGINT NOT NULL,

                           title VARCHAR(300) NOT NULL,
                           content VARCHAR(1000) NOT NULL,

                           is_answered BOOLEAN NOT NULL DEFAULT FALSE,
                           answered_at TIMESTAMP NULL,

                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP NULL,

                           is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
                           deleted_at TIMESTAMP NULL
);

COMMENT ON TABLE inquiries IS '1:1 문의';

-- =========================================
-- 문의 답변
-- =========================================
CREATE TABLE inquiry_answers (
                                 inquiry_answer_id BIGSERIAL PRIMARY KEY,

                                 inquiry_id BIGINT NOT NULL,

                                 content VARCHAR(2000) NOT NULL,

                                 created_by BIGINT NOT NULL,
                                 updated_by BIGINT,

                                 created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 updated_at TIMESTAMP,

                                 is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
                                 deleted_at TIMESTAMP
);

COMMENT ON TABLE inquiry_answers IS '문의 답변';

-- =========================================
-- 만료 재료 이력
-- =========================================
CREATE TABLE expired_products (
                                  expired_product_id BIGSERIAL PRIMARY KEY,

                                  member_id BIGINT NOT NULL,

                                  product_id BIGINT,
                                  product_category_id BIGINT,

                                  product_name VARCHAR(100) NOT NULL,
                                  category_name VARCHAR(100),

                                  created_at DATE NOT NULL
);

COMMENT ON TABLE expired_products IS '만료 재료 이력';

-- =========================================
-- 등급
-- =========================================
CREATE TABLE grades (
                        grade_id BIGSERIAL PRIMARY KEY,

                        name VARCHAR(30) NOT NULL,

                        min_score INTEGER NOT NULL,
                        max_score INTEGER NOT NULL,

                        CONSTRAINT uk_grades_name UNIQUE(name)
);

COMMENT ON TABLE grades IS '회원 등급';

-- =========================================
-- 점수
-- =========================================
CREATE TABLE scores (
                        score_id BIGSERIAL PRIMARY KEY,

                        member_id BIGINT NOT NULL,

                        score INTEGER NOT NULL DEFAULT 10,

                        updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                        CONSTRAINT uk_scores_member UNIQUE(member_id)
);

COMMENT ON TABLE scores IS '회원 점수';

-- =========================================
-- 점수 이력
-- =========================================
CREATE TABLE score_histories (
                                 score_history_id BIGSERIAL PRIMARY KEY,

                                 member_id BIGINT NOT NULL,

                                 score_reason VARCHAR(100) NOT NULL,

                                 target_name VARCHAR(100),
                                 target_id BIGINT,

                                 product_category_id BIGINT,

                                 score_delta INTEGER NOT NULL,

                                 created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON TABLE score_histories IS '점수 변경 이력';

-- =========================================
-- 비선호 재료
-- (운영 DB에는 uk_member_avoid_products 외에 이름만 다른 동일 제약이
--  하나 더 있음: ukurctqpeus8qbit5wsd76tl0o - 중복이므로 여기선 1개만 선언)
-- =========================================
CREATE TABLE member_avoid_products (
                                       member_avoid_product_id BIGSERIAL PRIMARY KEY,

                                       member_id BIGINT NOT NULL,
                                       product_id BIGINT NOT NULL,

                                       CONSTRAINT uk_member_avoid_products
                                           UNIQUE(member_id, product_id)
);

COMMENT ON TABLE member_avoid_products IS '회원 비선호 재료';

-- =========================================
-- 음식 카테고리
-- =========================================
CREATE TABLE food_categories (
                                 food_category_id BIGSERIAL PRIMARY KEY,

                                 name VARCHAR(100) NOT NULL,

                                 CONSTRAINT uk_food_categories_name UNIQUE(name)
);

COMMENT ON TABLE food_categories IS '음식 카테고리';

-- =========================================
-- 선호 음식
-- (운영 DB에는 uk_member_favorite_foods 외에 이름만 다른 동일 제약이
--  하나 더 있음: uka2hwqd0jblhsnd1wocx89wxj0 - 중복이므로 여기선 1개만 선언)
-- =========================================
CREATE TABLE member_favorite_foods (
                                       member_favorite_food_id BIGSERIAL PRIMARY KEY,

                                       member_id BIGINT NOT NULL,
                                       food_category_id BIGINT NOT NULL,

                                       CONSTRAINT uk_member_favorite_foods
                                           UNIQUE(member_id, food_category_id)
);

COMMENT ON TABLE member_favorite_foods IS '회원 선호 음식';

-- =========================================
-- 알림
-- =========================================
CREATE TABLE notifications (
                               notification_id BIGSERIAL PRIMARY KEY,

                               member_id BIGINT NOT NULL,

                               type VARCHAR(50) NOT NULL,
                               title VARCHAR(200) NOT NULL,
                               content VARCHAR(1000) NOT NULL,

                               target_type VARCHAR(50)
                                   CHECK (target_type IN ('FRIDGE_ITEM', 'INQUIRY', 'COMMENT', 'RECIPE')),
                               target_id BIGINT,
                               target_expiry_date DATE,
                               read_at TIMESTAMP,

                               created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON TABLE notifications IS '회원 알림';

CREATE INDEX idx_notifications_member_read_created
    ON notifications(member_id, read_at, created_at DESC);

-- 동일 대상(target)에 대한 중복 알림 생성 방지용 부분 UNIQUE 인덱스
CREATE UNIQUE INDEX uq_notifications_target
    ON notifications(member_id, type, target_type, target_id, target_expiry_date)
    WHERE target_type IS NOT NULL AND target_id IS NOT NULL AND target_expiry_date IS NOT NULL;

-- =========================================
-- 리프레시 토큰
-- =========================================
CREATE TABLE refresh_tokens (
                                refresh_token_id BIGSERIAL PRIMARY KEY,

                                member_id BIGINT NOT NULL,

                                refresh_token VARCHAR(1000) NOT NULL,

                                expired_at TIMESTAMP NOT NULL,

                                created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP,

                                CONSTRAINT uq_refresh_tokens_refresh_token UNIQUE(refresh_token)
);

COMMENT ON TABLE refresh_tokens IS '리프레시 토큰';

-- =========================================
-- 재료 카테고리
-- =========================================
CREATE TABLE product_categories (
                                    product_category_id BIGSERIAL PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL UNIQUE
);

COMMENT ON TABLE product_categories IS '재료 카테고리';

-- =========================================
-- 재료
-- =========================================
CREATE TABLE products (
                          product_id BIGSERIAL PRIMARY KEY,
                          product_category_id BIGINT NOT NULL,

                          name VARCHAR(100) NOT NULL,
                          default_expiry_days INTEGER NULL,

                          is_active BOOLEAN NOT NULL DEFAULT TRUE,

                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NULL,

                          CONSTRAINT uk_products_name UNIQUE (name)
);

COMMENT ON TABLE products IS '사전 재료';

CREATE INDEX idx_products_category
    ON products(product_category_id);

CREATE INDEX idx_products_active
    ON products(is_active);

-- =========================================
-- 레시피 카테고리
-- =========================================
CREATE TABLE recipe_categories (
                                   recipe_category_id BIGSERIAL PRIMARY KEY,
                                   name VARCHAR(100) NOT NULL UNIQUE
);

COMMENT ON TABLE recipe_categories IS '레시피 카테고리';

-- =========================================
-- 레시피
-- =========================================
CREATE TABLE recipes (
                         recipe_id BIGSERIAL PRIMARY KEY,

                         recipe_category_id BIGINT NOT NULL,
                         created_by BIGINT NULL,

                         -- TODO(고아 컬럼): 과거 Recipe.foodCategory(@ManyToOne) 필드가
                         -- recipe_food_categories 매핑 테이블로 리팩토링되며 Entity에서는
                         -- 제거됐지만 운영 DB 컬럼+FK는 남아있다. 현재 코드에서는 사용하지 않는다.
                         food_category_id BIGINT NULL,

                         name VARCHAR(100) NOT NULL,
                         description VARCHAR(1000) NULL,

                         cooking_time INTEGER NULL DEFAULT 15,

                         difficulty VARCHAR(20) NOT NULL
                             CHECK (difficulty IN ('EASY', 'NORMAL', 'HARD'))
                                                   DEFAULT 'EASY',

                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP NULL,

                         is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
                         deleted_at TIMESTAMP NULL
);

COMMENT ON TABLE recipes IS '레시피';

CREATE INDEX idx_recipes_category
    ON recipes(recipe_category_id);

CREATE INDEX idx_recipes_deleted
    ON recipes(is_deleted);

CREATE INDEX idx_recipes_name
    ON recipes(name);

-- =========================================
-- 레시피 필수 재료
-- =========================================
CREATE TABLE recipe_required_products (
                                          recipe_required_product_id BIGSERIAL PRIMARY KEY,

                                          recipe_id BIGINT NOT NULL,
                                          product_id BIGINT NOT NULL,

                                          CONSTRAINT uk_recipe_required_products
                                              UNIQUE(recipe_id, product_id)
);

COMMENT ON TABLE recipe_required_products IS '레시피 필수 재료';

CREATE INDEX idx_rrp_recipe
    ON recipe_required_products(recipe_id);

CREATE INDEX idx_rrp_product
    ON recipe_required_products(product_id);

-- =========================================
-- 레시피 조리 순서
-- =========================================
CREATE TABLE recipe_steps (
                              recipe_step_id BIGSERIAL PRIMARY KEY,

                              recipe_id BIGINT NOT NULL,

                              step_no INTEGER NOT NULL,
                              content VARCHAR(2000) NOT NULL,

                              is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
                              deleted_at TIMESTAMP NULL,

                              CONSTRAINT uk_recipe_steps
                                  UNIQUE(recipe_id, step_no)
);

COMMENT ON TABLE recipe_steps IS '레시피 조리 순서';

CREATE INDEX idx_recipe_steps_recipe
    ON recipe_steps(recipe_id);

-- =========================================
-- 레시피-음식카테고리 매핑
-- TODO(제약 누락): "레시피당 1개만 허용"이 설계 의도이나, 운영 DB에는
-- recipe_id에 대한 UNIQUE 제약이 반영되어 있지 않다(PK만 존재).
-- 아래는 운영 DB 실제 상태를 그대로 반영한 것이며, 의도대로 강제하려면
-- 별도 마이그레이션으로 `ALTER TABLE recipe_food_categories
-- ADD CONSTRAINT uk_recipe_food_categories_recipe UNIQUE(recipe_id);`를 추가할 것.
-- =========================================
CREATE TABLE recipe_food_categories (
                                        recipe_food_category_id BIGSERIAL PRIMARY KEY,

                                        recipe_id        BIGINT NOT NULL,
                                        food_category_id BIGINT NOT NULL
);

COMMENT ON TABLE recipe_food_categories IS '레시피-음식카테고리 매핑';

-- =========================================
-- 이메일 인증
-- =========================================
CREATE TABLE email_verifications (
                                      email_verification_id BIGSERIAL PRIMARY KEY,

                                      email VARCHAR(255) NOT NULL,
                                      code VARCHAR(6) NOT NULL,

                                      verified BOOLEAN NOT NULL,
                                      attempt_count INTEGER NOT NULL,

                                      expires_at TIMESTAMP NOT NULL,
                                      verified_at TIMESTAMP,

                                      created_at TIMESTAMP NOT NULL
);

COMMENT ON TABLE email_verifications IS '이메일 인증 코드';

-- =========================================
-- FK(외래키) 제약조건
-- 운영 DB(pg_dump) 기준 실제로 존재하는 FK는 아래 7건 뿐이다.
-- Entity에서 @ManyToOne으로 실제 연관관계 객체를 매핑한 곳에만
-- Hibernate가 자동으로 생성한 것으로 보이며, 그 외 테이블 간 관계는
-- 전부 순수 컬럼(Long)만 있고 DB 레벨 FK는 없다.
-- =========================================
ALTER TABLE recipes
    ADD CONSTRAINT fk_recipes_recipe_category
        FOREIGN KEY (recipe_category_id) REFERENCES recipe_categories(recipe_category_id);

-- 고아 FK: 위 recipes.food_category_id TODO 참고
ALTER TABLE recipes
    ADD CONSTRAINT fk_recipes_food_category
        FOREIGN KEY (food_category_id) REFERENCES food_categories(food_category_id);

ALTER TABLE member_avoid_products
    ADD CONSTRAINT fk_member_avoid_products_member
        FOREIGN KEY (member_id) REFERENCES members(member_id);

ALTER TABLE member_avoid_products
    ADD CONSTRAINT fk_member_avoid_products_product
        FOREIGN KEY (product_id) REFERENCES products(product_id);

ALTER TABLE member_favorite_foods
    ADD CONSTRAINT fk_member_favorite_foods_member
        FOREIGN KEY (member_id) REFERENCES members(member_id);

ALTER TABLE member_favorite_foods
    ADD CONSTRAINT fk_member_favorite_foods_food_category
        FOREIGN KEY (food_category_id) REFERENCES food_categories(food_category_id);

ALTER TABLE refresh_tokens
    ADD CONSTRAINT fk_refresh_tokens_member
        FOREIGN KEY (member_id) REFERENCES members(member_id);

-- =========================================
-- pg_trgm + GIN index
-- =========================================
CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE INDEX IF NOT EXISTS idx_products_name_trgm
    ON products
        USING gin (name gin_trgm_ops);
