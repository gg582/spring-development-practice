package com.example.app.crypto;

import com.example.app.crypto.algorithms.DecryptManager;

public interface FileDecryptor {
    void decryptFile(String sourcePath, String targetPath) throws Exception;
    void dummyCheck();
    void setDecryptManager(DecryptManager manager);
}

