package com.example.decrypt;

import com.example.decrypt.algorithms.DecryptManager;

public class FileDecryptorFactory {
    private DecryptManager decryptManager;

    public void setDecryptManager(DecryptManager decryptManager) {
        this.decryptManager = decryptManager;
    }

    public FileDecryptorFactory() {
        System.out.println("FileDecryptorFactory 빈이 생성되었습니다.");
    }

    public FileDecryptor createFileDecryptor() {
        System.out.println("FileDecryptor를 생성합니다.");
        FileDecryptor fileDecryptor = new FileDecryptor();
        fileDecryptor.setDecryptManager(decryptManager);
        return fileDecryptor;
    }
}
