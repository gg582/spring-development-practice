package com.example.hello_spring;

public class IntroduceMusician {
	
	public String introduce(String gender, String name) {

		// Hello, Musician. 까지는 제거합니다.
		// 이 부분은 장르마다 따로 각 장르 클래스가 구현합니다.
		// 각 장르 클래스는 Musician이라는 인터페이스에 대한 구현입니다.
		return "You are " + gender + ". " + name + ", Right?";
	}
}
