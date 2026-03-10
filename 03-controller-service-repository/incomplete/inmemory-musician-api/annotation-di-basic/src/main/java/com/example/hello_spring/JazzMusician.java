package com.example.hello_spring;
import org.springframework.stereotype.Component;

@Component // 컴포넌트를 통한 인젝션.
public class JazzMusician implements Musician {
	private final IntroduceMusician introduceMusician;

	// IntroduceMusician 타입의 객체를 수동으로 할당하지 않습니다.
	// XML DI가 new IntroduceMusician()을 실행한 후에
	// 클래스의 this.introduceMusician에 값을 대입합니다.
	// 이러한 과정을 거치기 때문에 this.introduceMusician를 위해서 this.introduceMusician = new IntroduceMusician을
	// 하지 않습니다.
	public JazzMusician(IntroduceMusician introduceMusician) {
		System.out.println("Constructor for JazzMusician triggered");
		this.introduceMusician = introduceMusician;
	}

	// 생성, 초기화 후 실행
	public void init() {
		System.out.println("Initialize JazzMusician via init()");
	}

	// 소멸 전 실행
	public void destroy() {
		System.out.println("Destroy JazzMusician via destroy()");
	}

	// 이것은 이 클래스의 해시값을 반환합니다.
	public int identity() {
    return System.identityHashCode(this);
  }

	public String getIntroduce(String gender, String name) {
		return "Hello, Jazz Musician, " + this.introduceMusician.introduce(gender, name);
	}

}

