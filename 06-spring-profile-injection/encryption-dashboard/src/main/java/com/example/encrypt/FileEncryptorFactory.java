package com.example.encrypt;

import com.example.encrypt.algorithms.EncryptManager;

public class FileEncryptorFactory {
    private EncryptManager encryptManager;

    public void setEncryptManager(EncryptManager encryptManager) {
        this.encryptManager = encryptManager;
    }

    public FileEncryptorFactory() {
        System.out.println("FileEncryptorFactory 빈이 생성되었습니다.");
    }

    public FileEncryptor createFileEncryptor() {
        System.out.println("FileEncryptor를 생성합니다.");
        FileEncryptor fileEncryptor = new FileEncryptor();
        fileEncryptor.setEncryptManager(encryptManager);
        return fileEncryptor;
    }
}
