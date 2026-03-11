package com.example.hello_spring;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class HelloSpring {
	private final MusicianService musicianService;

	// 이것은 최소한의 받은 인자들에 대한 인삿말 조합을 하기 위해 클래스를 현재 HelloSpring 컨트롤러의 멤버로 대입합니다.
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
	public HelloSpring(MusicianService musicianService) {
		this.musicianService = musicianService;
	}

	// 어노테이션 DI에서의 PostMapping은 생성자에 붙이는 것이 아니라, 클래스의 멤버 변수에 붙이는 것입니다.
	// 생성자에 붙이는 것은 생성자 DI입니다. 어노테이션 DI는 클래스의 멤버 변수에 붙이는 것입니다. 생성자 DI는 생성자에 붙이는 것입니다.
	// 둘은 서로 다른 DI 방식입니다.
	// musicians 정보를 등록하기 위한 것은 매핑을 가져오는 것이 아닌 등록하는 것이므로 PostMapping을 사용하면 됩니다. GetMapping은 정보를 가져오는 것이므로 GetMapping을 사용하면 됩니다.
	@PostMapping("/musicians/register")
	public MusicianProfile register(@RequestParam String name,
	                                @RequestParam String gender,
	                                @RequestParam String genre) {
		return musicianService.register(name, gender, genre);
	}

	@GetMapping("/musicians")
	public java.util.List<MusicianProfile> findAll() {
		return musicianService.findAll();
	}

	@GetMapping("/musicians/by-name")
	public MusicianProfile findByName(@RequestParam String name) {
		return musicianService.findByName(name);
	}

	@PostMapping("/musicians/change-genre")
	public MusicianProfile changeGenre(@RequestParam Long id,
	                                   @RequestParam String genre) {
		return musicianService.changeGenre(id, genre);
	}
}
