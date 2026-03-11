package com.example.hello_spring;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// REST 컨트롤러 Advice. 이것이 예외 핸들러에 붙어야 하는 어노테이션이다.
@RestControllerAdvice
public class GlobalExceptionHandler {
	// 예외 핸들러에 대한 어노테이션이다.
	// 각각이 각 예외 처리에 대해 담당한다.
	@ExceptionHandler(DuplicateMusicianException.class)
	public ResponseEntity<ErrorResponse> handleDuplicateMusician(
			DuplicateMusicianException ex, HttpServletRequest request) { // 요청과 예외 클래스를 받는다
		ErrorResponse response = new ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), // HttpStatus는 BAD Request, 즉 나쁜 요청이다.
				ex.getMessage(), // 받아 놨던 DuplicateMusicianException 클래스로부터 오류 메시지를 받는다.
				request.getRequestURI() // 요청 URI를 구하기 위해서 인자로 HttpServletRequest를 받아온 것이다.
		);
		
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // 적절한 예외 엔터티 안에 응답들 담아서 리턴한다.
  }

	@ExceptionHandler(MusicianNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleMusicianNotFound(
			MusicianNotFoundException ex, HttpServletRequest request) {
		ErrorResponse response = new ErrorResponse(
				HttpStatus.NOT_FOUND.value(),
				ex.getMessage(),
				request.getRequestURI()
	  );
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class) 
	public ResponseEntity<ErrorResponse> handleValidationError(
			MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		String message = ex.getBindingResult()
			.getFieldErrors()
			.stream()
			.findFirst()
			.map(error -> error.getField() + ": " + error.getDefaultMessage())
			.orElse("Validation failed");

		ErrorResponse response = new ErrorResponse(
				HttpStatus.BAD_REQUEST.value(),
				message,
				request.getRequestURI()
		);
		return ResponseEntity.badRequest().body(response);
	}
}
