package com.example.hello_spring;


public class JazzMusician implements Musician {
	private IntroduceMusician introduceMusician;

	// IntroduceMusician 타입의 객체를 수동으로 할당하지 않습니다.
	// XML DI가 new IntroduceMusician()을 실행한 후에
	// 클래스의 this.introduceMusician에 값을 대입합니다.
	// 이러한 과정을 거치기 때문에 this.introduceMusician를 위해서 this.introduceMusician = new IntroduceMusician을
	// 하지 않습니다.
	public void setIntroduceMusician(IntroduceMusician introduceMusician) {
		this.introduceMusician = introduceMusician;
	}

	public String getIntroduce(String gender, String name) {
		return "Hello, Jazz Musician, " + this.introduceMusician.introduce(gender, name);
	}

}

