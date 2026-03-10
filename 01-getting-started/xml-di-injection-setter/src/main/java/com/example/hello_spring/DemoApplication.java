package com.example.hello_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class DemoApplication {
	public static void main(String[] args) {
		// 여기서 XML DI Injection을 수행합니다.
		// 단계 1: beans.xml을 로딩합니다.
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		// 단계 2: jazzMusician이라는 클래스에 맞게 가져온 bean의 내용대로 인젝션합니다.
		// ** 변경사항 **: jazzMusician만 일괄로 bluesMusician으로 바꾸면 바로 그대로 동작합니다.
		JazzMusician jazzMusician = context.getBean("jazzMusician", JazzMusician.class);
		// 단계 3: 스프링 애플리케이션을 시작합니다.
		// 이곳이 이 웹 애플리케이션의 진입점입니다.
		SpringApplication.run(DemoApplication.class, args);
	}

}
