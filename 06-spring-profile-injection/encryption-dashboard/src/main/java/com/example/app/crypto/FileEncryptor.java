package com.example.app.crypto;
import com.example.app.crypto.algorithms.EncryptManager;

public interface FileEncryptor {
    void encryptFile(String sourcePath, String targetPath) throws Exception;
    void dummyCheck();
    void setEncryptManager(EncryptManager manager);
    EncryptManager getEncryptManager();
}
