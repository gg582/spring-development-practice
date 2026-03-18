-- 테이블 생성
CREATE TABLE IF NOT EXISTS musicians (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    genre VARCHAR(255)
);

-- 샘플 데이터 삽입
INSERT INTO musicians (name, genre) VALUES ('Bo Carter', 'Blues') ON CONFLICT(name) DO NOTHING;
INSERT INTO musicians (name, genre) VALUES ('Robert Johnson', 'Blues') ON CONFLICT(name) DO NOTHING;
INSERT INTO musicians (name, genre) VALUES ('Yoo Jae-ha', 'Ballad') ON CONFLICT(name) DO NOTHING;
INSERT INTO musicians (name, genre) VALUES ('Cho Gi-cheol', 'Punk') ON CONFLICT(name) DO NOTHING;
INSERT INTO musicians (name, genre) VALUES ('Roman Neumoyev', 'Punk') ON CONFLICT(name) DO NOTHING;
INSERT INTO musicians (name, genre) VALUES ('Yegor Letov', 'Punk') ON CONFLICT(name) DO NOTHING;
