## PostgreSQL의 구문 차이
MySQL의 AUTO_INCREMENT는 SERIAL이 대체한다.

## JSP 페이지
register.jsp를 두어 브라우저에서 이름, 장르를 입력하면 음악가 정보가 추가되는 간단한 페이지

## 데이터 영속성
킬 때마다 테이블 DROP하지 않고 유지
### UNIQUE를 이용한 중복 방지
name VARCHAR(255) NOT NULL UNIQUE하여 이름이 중복되지 않게 해야 함.
이름을 기준으로 중복일 시 아무 동작도 하지 않기?
ON CONFLICT(name) DO NOTHING;
