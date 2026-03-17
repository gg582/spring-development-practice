package com.example.service;

import com.example.encrypt.FileEncryptorFactory;
import com.example.encrypt.algorithms.EncryptManager;

import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;

@Service
public class FileService {
    public byte[] encryptFile(String content, String b64Password) throws Exception {
        EncryptManager encryptManager = new FileEncryptorFactory().createFileEncryptor().getEncryptManager();
        byte[] password = java.util.Base64.getDecoder().decode(b64Password);
        SecretKey secretKey = new javax.crypto.spec.SecretKeySpec(password, encryptManager.getAlgorithm());
        return encryptManager.encryptBytes(content.getBytes(), secretKey);
    }
}
