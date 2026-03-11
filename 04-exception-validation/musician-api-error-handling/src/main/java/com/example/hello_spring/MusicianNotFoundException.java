package com.example.hello_spring;

public class MusicianNotFoundException extends RuntimeException {
	public MusicianNotFoundException(String message) {
		super(message);
	}
}
