-- =========================================
-- 회원
-- =========================================
CREATE TABLE members (
                         member_id BIGSERIAL PRIMARY KEY,

                         email VARCHAR(100) NOT NULL,
                         password VARCHAR(255) NOT NULL,
                         nickname VARCHAR(50) NOT NULL,

                         role VARCHAR(20) NOT NULL
                             CHECK (role IN ('USER', 'ADMIN'))
                                                       DEFAULT 'USER',

                         household_type VARCHAR(20) NOT NULL,

                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP,

                         is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
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
                                updated_at TIMESTAMP,

                                is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
                                deleted_at TIMESTAMP
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
                        -- grade_id BIGINT NOT NULL,

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

                                 target_type VARCHAR(100),
                                 target_id BIGINT,

                                 product_category_id BIGINT,

                                 score_delta INTEGER NOT NULL,

                                 -- description VARCHAR(500),

                                 created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON TABLE score_histories IS '점수 변경 이력';

-- =========================================
-- 비선호 재료
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

                               target_type VARCHAR(50),
                               target_id BIGINT,
                               target_expiry_date DATE,
                               read_at TIMESTAMP,

                               created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON TABLE notifications IS '회원 알림';

-- =========================================
-- 리프레시 토큰
-- =========================================
CREATE TABLE refresh_tokens (
                                refresh_token_id BIGSERIAL PRIMARY KEY,

                                member_id BIGINT NOT NULL,

                                refresh_token VARCHAR(1000) NOT NULL,

                                expired_at TIMESTAMP NOT NULL,

                                created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP
);

COMMENT ON TABLE refresh_tokens IS '리프레시 토큰';

-- =========================================
-- 재료 카테고리
-- =========================================
CREATE TABLE product_categories (
                                    product_category_id BIGSERIAL PRIMARY KEY,
                                    name VARCHAR(100) NOT NULL UNIQUE
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

CREATE INDEX idx_recipes_created_by
    ON recipes(created_by);

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

CREATE TABLE recipe_food_categories (
                                        recipe_food_category_id BIGSERIAL PRIMARY KEY,

                                        recipe_id        BIGINT NOT NULL,
                                        food_category_id BIGINT NOT NULL,

                                        CONSTRAINT uk_recipe_food_categories_recipe
                                            UNIQUE(recipe_id)
);

COMMENT ON TABLE recipe_food_categories IS '레시피-음식카테고리 매핑 (레시피당 1개, recipe_id UNIQUE)';

CREATE INDEX idx_recipe_food_categories_food_category
    ON recipe_food_categories(food_category_id);

-- =========================================
-- pg_trgm + GIN index
-- =========================================
CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE INDEX IF NOT EXISTS idx_products_name_trgm
    ON products
        USING gin (name gin_trgm_ops);
