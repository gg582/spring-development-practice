package com.example.app.crypto.factory;

import com.example.app.crypto.FileEncryptor;
import com.example.app.crypto.algorithms.AESEncryptManager;
import com.example.app.crypto.algorithms.EncryptManager;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class FileEncryptorFactory {
    private EncryptManager encryptManager = new AESEncryptManager();

    public void setEncryptManager(EncryptManager encryptManager) {
        this.encryptManager = encryptManager;
    }

    public FileEncryptor createFileEncryptor() {
        return new FileEncryptor() {
            private EncryptManager manager = encryptManager;

            @Override
            public void encryptFile(String sourcePath, String targetPath) throws Exception {
                byte[] data = Files.readAllBytes(Paths.get(sourcePath));
                // TODO: 실제 환경에 맞는 키 관리 로직 필요 (임시 16바이트)
                SecretKey key = new SecretKeySpec(new byte[16], manager.getAlgorithm());
                byte[] encrypted = manager.encryptBytes(data, key);
                Files.write(Paths.get(targetPath), encrypted);
            }

            @Override
            public void dummyCheck() {
                System.out.println("Encryptor Ready: " + manager.getAlgorithm());
            }

            @Override
            public void setEncryptManager(EncryptManager m) {
                this.manager = m;
            }

            @Override
            public EncryptManager getEncryptManager() {
                return this.manager;
            }
        };
    }
}
