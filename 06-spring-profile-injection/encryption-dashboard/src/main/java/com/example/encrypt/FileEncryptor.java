package com.example.encrypt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.Security;

import com.example.encrypt.algorithms.EncryptManager;

public class FileEncryptor {
    private EncryptManager encryptManager;

    public void setEncryptManager(EncryptManager encryptManager) {
        this.encryptManager = encryptManager;
    }

    public EncryptManager getEncryptManager() {
        return encryptManager;
    }

    public void init() {
        System.out.println("FileEncryptor 클래스가 준비되었습니다.");
        if (encryptManager != null) {
            System.out.println("활성화된 EncryptManager: " + encryptManager.getClass().getSimpleName());
        }
    }

    // 바운시 캐슬을 자바의 보안 프로바이더로 등록합니다.
    // 바운시 캐슬은 기본 JCE가 아닌 서드 파티이기 때문에 등록 절차를 거치지 않으면
    // 제대로 사용될 수 없습니다.
    static void setupBouncyCastle() {
        Security.addProvider(new BouncyCastleProvider());
    }

    // java.nio.file.Files를 이용하면 저수준 I/O를 직접 구현하지 않아도 통째로 파일을 읽어 리턴할 수 있다.
    public static byte[] LoadFile(String path) throws IOException {
        Path filePath = Paths.get(path);
        return Files.readAllBytes(filePath);
    }

    public static boolean veirfyBytes(byte[] b) {
        if (b == null)
            return false;
        if (b.length == 0)
            return false;
        return true;
    }

    public void checkUser() {
        System.out.println("FileEncryptor: 유저를 체크합니다...(아직 더미 구현입니다)");
    }
}
