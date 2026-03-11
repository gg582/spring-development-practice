package com.example.hello_spring;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class HelloSpring {
	// 이것은 최소한의 받은 인자들에 대한 인삿말 조합을 하기 위해 클래스를 현재 HelloSpring 컨트롤러의 멤버로 대입합니다.
	private final Musician musician;
	// 현재 이것을 사용하는 클래스는 BluesMusician입니다.
	// 추후 XML DI 실습을 위해서 추가 클래스를 선언할 것입니다.
	// DATE: 2026-03-10 15:35 UTC+0900 작성
	// ============================= DONE ==============================================
	// 1. BluesMusician.java의 클래스 구조 파악
	// 2. 그것을 모방해서 ClassicalMusician, JazzMusician, PunkMusician 만들기
	// 3. 셋 모두가 서로 다르게 동작해야 알아볼 수 있음. getMessage의 인자 말고
	//    각 클래스에서 초기화 함수 호출 시 장르명을 저장하게 하고,
	//    출력 포맷을 "Hello, Punk Musician, You are Mr. Roman Neumoyev, Right?
	//    와 같은 방식으로 바꾼다.
	// 효과: XML DI를 변경하는 것만으로 완전히 다른 클래스가 호출되어 동작한다.
	// =================================================================================
	@Autowired
	public HelloSpring(Musician musician) {
		this.musician = musician;
	}

	@GetMapping("/musician/get-introduce") // 중요: 여러 XML DI를 인젝션합니다. 범용적인 이름인 musician으로 변경합니다.
	public String getIntroduce(@RequestParam String gender, @RequestParam String name) {
		return musician.getIntroduce(gender, name);
	}

	@GetMapping("/hello-spring")
	public String HelloSpring() {
		return "Hello, World!";
	}

}
