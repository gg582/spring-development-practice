package com.example.hello_spring;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class ErrorResponse {
	private int status;
	private String message;
	private String path;

	public ErrorResponse(int status, String message, String path) {
		this.status = status;
		this.message = message;
		this.path = path;
	
	}

	// get-set을 한 묶음으로 해서 값 설정-값 반환으로 각 클래스 멤버에 대한 getter/setter 만들기
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
}
