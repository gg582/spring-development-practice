package com.example.hello_spring;

public class MusicianProfile {
	private Long id;
	private String name;
	private String gender;
	private String genre;

	// 뮤지션 프로파일의 생성자에 값이 없을 시에는 일단 비워 둡니다.
	// 하나의 프린트문 정도만 넣어주면 좋습니다.
	public MusicianProfile() {
		System.out.println("MusicianProfile successfully created");
	}


	// 생성자에 값을 넣어 줄 시에는 그 값을 이용해서 채워 줍니다.
	// 모든 값이 잘 채워지고 나면 프린트문을 넣어 줍니다.
	public MusicianProfile(Long id, String name, String gender, String genre) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.genre = genre;

		System.out.println("MusicianProfile successfully created");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
}
