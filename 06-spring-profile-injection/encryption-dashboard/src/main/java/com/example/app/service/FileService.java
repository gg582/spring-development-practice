package com.example.app.service;

import com.example.app.crypto.factory.FileEncryptorFactory;
import com.example.app.crypto.algorithms.EncryptManager;

import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;

@Service
public class FileService {
    EncryptManager encryptManager;

    public byte[] encryptFile(String content, String b64Password) throws Exception {
        this.encryptManager = new FileEncryptorFactory().createFileEncryptor().getEncryptManager();
        byte[] password = java.util.Base64.getDecoder().decode(b64Password);
        SecretKey secretKey = new javax.crypto.spec.SecretKeySpec(password, encryptManager.getAlgorithm());
        return encryptManager.encryptBytes(content.getBytes(), secretKey);
    }

    public EncryptManager getEncryptManager() {
        return this.encryptManager;
    }
}
