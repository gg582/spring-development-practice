package com.example.hello_spring;
import org.springframework.stereotype.Component;

@Component // 컴포넌트를 통한 인젝션.
// 실무에서는 XML 인젝션보다 이러한 어노테이션 기반의 인젝션을 수행합니다.
public class IntroduceMusician {
	public String introduce(String gender, String name) {
		// Hello, Musician. 까지는 제거합니다.
		// 이 부분은 장르마다 따로 각 장르 클래스가 구현합니다.
		// 각 장르 클래스는 Musician이라는 인터페이스에 대한 구현입니다.
		return "You are " + gender + ". " + name + ", Right?";
	}
}
