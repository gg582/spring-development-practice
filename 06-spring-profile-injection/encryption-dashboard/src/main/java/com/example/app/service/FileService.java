package com.example.app.service;

import com.example.app.crypto.algorithms.EncryptManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;

// 서비스로직이 이곳에 위치함
@Service
public class FileService {
    @Autowired
    private EncryptManager encryptManager;

    public byte[] encryptFile(byte[] content, String b64Password) throws Exception {
        byte[] password = java.util.Base64.getDecoder().decode(b64Password);
        // 생성된 encryptManager의 알고리즘은 코드가 아닌 applicationContext.xml에 영향을 받음
        SecretKey secretKey = new javax.crypto.spec.SecretKeySpec(password, encryptManager.getAlgorithm());
        // 함수가 수신한 byte[]와 base64 인코딩된 비밀번호를 통해 암호화된 데이터를 만듦
        return encryptManager.encryptBytes(content, secretKey);
    }

    public EncryptManager getEncryptManager() {
        return this.encryptManager;
    }
}
