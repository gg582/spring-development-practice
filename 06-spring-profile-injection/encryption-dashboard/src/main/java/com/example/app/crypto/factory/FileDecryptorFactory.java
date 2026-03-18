package com.example.app.crypto.factory;

import com.example.app.crypto.FileDecryptor;
import com.example.app.crypto.algorithms.AESDecryptManager;
import com.example.app.crypto.algorithms.DecryptManager;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class FileDecryptorFactory {
    private DecryptManager decryptManager = new AESDecryptManager();

    public void setDecryptManager(DecryptManager decryptManager) {
        this.decryptManager = decryptManager;
    }

    public FileDecryptor createFileDecryptor() {
        return new FileDecryptor() {
            private DecryptManager manager = decryptManager;

            @Override
            public void decryptFile(String sourcePath, String targetPath) throws Exception {
                byte[] data = Files.readAllBytes(Paths.get(sourcePath));
                SecretKey key = new SecretKeySpec(new byte[16], manager.getAlgorithm());
                byte[] decrypted = manager.decryptBytes(data, key);
                Files.write(Paths.get(targetPath), decrypted);
            }

            @Override
            public void dummyCheck() {
                System.out.println("Decryptor Ready.");
            }

            @Override
            public void setDecryptManager(DecryptManager m) {
                this.manager = m;
            }
        };
    }
}
