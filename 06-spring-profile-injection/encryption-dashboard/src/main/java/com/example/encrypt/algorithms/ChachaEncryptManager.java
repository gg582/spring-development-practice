package com.example.encrypt.algorithms;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.ByteBuffer;
import java.security.SecureRandom;

public class ChachaEncryptManager implements EncryptManager {
    private static final String ENCRYPT_ALGO = "ChaCha20-Poly1305";
    public static final int NONCE_LEN = 12;

    @Override
    public String getAlgorithm() {
        return "ChaCha20";
    }

    // Nonce가 이미 주어진 경우에 대한 encryptBytes 구현입니다.
    @Override
    public byte[] encryptBytes(byte[] pText, SecretKey key) throws Exception {
        byte[] nonce = getNonce();
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        IvParameterSpec iv = new IvParameterSpec(nonce);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        return ByteBuffer.allocate(pText.length + NONCE_LEN)
                .put(pText)
                .put(nonce)
                .array();

    }

    // 논스는 지정된 길이인 12로 만들어 줍니다.
    private static byte[] getNonce() {
        byte[] newNonce = new byte[NONCE_LEN];
        new SecureRandom().nextBytes(newNonce);
        return newNonce;
    }
}
