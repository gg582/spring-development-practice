-- 기존 테이블 삭제 (재시작 시 초기화용)
DROP TABLE IF EXISTS musicians;

-- 테이블 생성
CREATE TABLE musicians (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    genre VARCHAR(255)
);

-- 샘플 데이터 삽입
INSERT INTO musicians (name, genre) VALUES ('Bo Carter', 'Blues');
INSERT INTO musicians (name, genre) VALUES ('Robert Johnson', 'Blues');
INSERT INTO musicians (name, genre) VALUES ('Yoo Jae-ha', 'Ballad');
INSERT INTO musicians (name, genre) VALUES ('Cho Gi-cheol', 'Punk');
INSERT INTO musicians (name, genre) VALUES ('Roman Neumoyev', 'Punk');
INSERT INTO musicians (name, genre) VALUES ('Yegor Letov', 'Punk');
