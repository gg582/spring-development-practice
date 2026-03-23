package com.example.app.crypto.algorithms;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.ByteBuffer;

public class ChachaDecryptManager implements DecryptManager {
    private static final String ENCRYPT_ALGO = "ChaCha20-Poly1305";
    public static final int NONCE_LEN = 12;

    @Override
    public String getAlgorithm() {
        return "ChaCha20";
    }

    @Override
    public byte[] decryptBytes(byte[] cText, SecretKey key) throws Exception {
        ByteBuffer buf = ByteBuffer.wrap(cText);
        // ========== cText를 nonce와 암호화된 데이터로 분리합니다. ===========
        /**
         * 주의: 이것은 getNonce와 getData로 추상화하지 말아야 합니다.
         * 이것을 추상화할 때의 문제점은 명확합니다.
         * 별도의 함수로 구현을 뺄 경우에는 this.buf로 불필요하게 클래스 안의 멤버를 만들거나,
         * buffer를 두 번의 함수 노출에서 반복적으로 인자로 복사해야 합니다.
         * 이러한 오버헤드를 줄이기 위해 이것에는 추상화를 하지 않고 순차적으로 읽는 형태를 노출하지 않을 수 없습니다.
         **/
        byte[] encrypted = new byte[cText.length - NONCE_LEN];
        byte[] nonce = new byte[NONCE_LEN];
        // 버퍼에서 읽어오는 순서는 암호화된 데이터 먼저, 그리고 nonce를 나중에 읽습니다.
        // 만약 더 심화된 보안을 원한다면 nonce를 숨기는 기법을 고민할 수 있습니다.
        buf.get(encrypted);
        buf.get(nonce);

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

        IvParameterSpec iv = new IvParameterSpec(nonce);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);

        // 복호화된 텍스트는 이곳에서 바로 반환합니다.
        return cipher.doFinal(encrypted);
    }
}
