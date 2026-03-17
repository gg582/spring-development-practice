package com.example.decrypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.Security;

import com.example.decrypt.algorithms.DecryptManager;

public class FileDecryptor {
    private DecryptManager decryptManager;

    public void setDecryptManager(DecryptManager decryptManager) {
        this.decryptManager = decryptManager;
    }

    public DecryptManager getDecryptManager() {
        return decryptManager;
    }

    public void init() {
        System.out.println("FileDecryptor가 준비되었습니다.");
        if (decryptManager != null) {
            System.out.println("활성화된 DecryptManager: " + decryptManager.getClass().getSimpleName());
        }
    }

    // 바운시 캐슬을 자바의 보안 프로바이더로 등록합니다.
    // 바운시 캐슬은 기본 JCE가 아닌 서드 파티이기 때문에 등록 절차를 거치지 않으면
    // 제대로 사용될 수 없습니다.
    static void setupBouncyCastle() {
        Security.addProvider(new BouncyCastleProvider());
    }

    public void checkUser() {
        System.out.println("FileDecryptor: 유저를 체크합니다...(아직 더미 구현입니다)");
    }
}
