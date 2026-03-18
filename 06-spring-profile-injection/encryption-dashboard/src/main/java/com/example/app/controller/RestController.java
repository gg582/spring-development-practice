package com.example.app.controller;

// ApplicationContext를 로딩하기 위해 반드시 이것이 필요함.
// 여기서 ApplicationContext 클래스를 가져오기 위한 선행 사항이 임포트된다.
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
// 여기서 Encrypt 내부 모든 클래스를 임포트한다.
import com.example.app.crypto.*;
import com.example.app.crypto.FileDecryptor;

// 여기서 스프링 구조에서 applicationContext.xml이 위치한 곳을 뒤져서
// applicationContext.xml이란 파일명을 찾아내는
// ClassPathXmlApplicationContext를 임포트한다.
// 이것은 보조적인 지원 도구이므로 support 하위에서 가져온다.
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RestController {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        if (context instanceof ConfigurableApplicationContext) {
            String[] activeProfiles = ((ConfigurableApplicationContext) context).getEnvironment().getActiveProfiles();
            System.out.println("Active Spring Profile: " + (activeProfiles.length > 0 ? activeProfiles[0] : "(none)"));
        }

        // 컨텍스트에 적힌대로 fileEncryptor가 FileEncrypytorService에 대한 이름이다.
        // 따라서 context.GetBean에서는 fileEncryptor가 이름, FileEncryptor가 실제 타입이 된다.
        FileEncryptor enc = context.getBean("fileEncryptor", FileEncryptor.class);
        FileDecryptor dec = context.getBean("fileDecryptor", FileDecryptor.class);
        // 아직 더미 구현, 향후 서비스 로직 추가되어야 함
        // TODO: 실제로 Encryption과 Decryption이 제대로 되는지 테스트 케이스 작성 필요

        enc.dummyCheck();
        dec.dummyCheck();
    }
}
