package com.example.hello_spring;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MusicianRegisterRequest {
	@NotBlank(message = "name must not be blank")
	@Size(max = 100, message = "name must be 100 characters or fewer")
	private String name;

	@NotBlank(message = "gender must not be blank")
	@Size(max = 50, message = "gender must be 50 characters or fewer")
	private String gender;

	@NotBlank(message = "genre must not be blank")
	@Size(max = 60, message = "genre must be 60 characters or fewer")
	private String genre;


	public MusicianRegisterRequest() {
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
