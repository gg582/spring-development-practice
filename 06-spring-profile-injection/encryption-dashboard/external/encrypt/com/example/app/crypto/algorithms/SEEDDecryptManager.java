package com.example.app.crypto.algorithms;

import com.example.app.crypto.algorithms.DecryptManager;
import com.example.app.crypto.algorithms.KISA_SEED_CBC; // KISA_SEED_CBC 클래스를 사용할 수 있다고 가정합니다.
import com.example.app.crypto.algorithms.KISA_SEED_CBC.KISA_SEED_INFO;
import com.example.app.crypto.algorithms.KISA_SEED_CBC.KISA_ENC_DEC;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * @brief SEED 복호화 관리자 구현체.
 * 
 * KISA_SEED_CBC 클래스를 사용하여 SEED 알고리즘으로 데이터를 복호화합니다.
 * 이 클래스는 DecryptManager 인터페이스를 구현하며, SEED 복호화 기능을 제공합니다.
 */
public class SEEDDecryptManager implements DecryptManager {

    private static final String ALGORITHM_NAME = "SEED"; // 알고리즘 이름
    private static final int KEY_SIZE_BYTES = 16; // SEED는 128비트 (16바이트) 키를 사용합니다.
    private static final int IV_SIZE_BYTES = 16; // SEED CBC 모드는 128비트 (16바이트) IV를 사용합니다.

    private KISA_SEED_INFO seedInfo; // SEED 암호화/복호화 상태 정보

    /**
     * @brief SEEDDecryptManager의 생성자.
     */
    public SEEDDecryptManager() {
        this.seedInfo = new KISA_SEED_INFO(); // SeedInfo 객체 초기화
        // 자바에서 동적으로 SEED Key와 IV를 생성합니다.
        byte[] defaultKey = new byte[KEY_SIZE_BYTES];
        byte[] defaultIV = new byte[IV_SIZE_BYTES];
        SecureRandom random = new SecureRandom();
        random.nextBytes(defaultKey);
        random.nextBytes(defaultIV);
    }

    /**
     * @brief 데이터를 SEED 알고리즘으로 복호화합니다.
     *
     * @param data 복호화할 암호화된 데이터 바이트 배열 (IV + 암호문).
     * @param key 복호화에 사용할 SecretKey 객체.
     * @return 복호화된 원본 데이터 바이트 배열.
     * @throws Exception 복호화 과정에서 오류 발생 시.
     */
    @Override
    public byte[] decryptBytes(byte[] data, SecretKey key) throws Exception {
        // SecretKey 객체에서 바이트 배열 키를 추출합니다.
        byte[] userKey = key.getEncoded();
        if (userKey.length != KEY_SIZE_BYTES) {
            byte[] paddedKey = new byte[KEY_SIZE_BYTES];
            System.arraycopy(userKey, 0, paddedKey, 0, Math.min(userKey.length, KEY_SIZE_BYTES));
            userKey = paddedKey;
        }

        if (data == null || data.length <= IV_SIZE_BYTES) {
            throw new IllegalArgumentException("Invalid data length for decryption.");
        }

        // 암호문 앞부분에서 동적으로 생성된 IV를 추출합니다.
        byte[] iv = new byte[IV_SIZE_BYTES];
        System.arraycopy(data, 0, iv, 0, IV_SIZE_BYTES);

        // 실제 복호화할 데이터 (IV를 제외한 나머지)
        byte[] cipherText = new byte[data.length - IV_SIZE_BYTES];
        System.arraycopy(data, IV_SIZE_BYTES, cipherText, 0, cipherText.length);

        // KISA_SEED_CBC의 SEED_CBC_Decrypt 메소드를 호출합니다.
        return KISA_SEED_CBC.SEED_CBC_Decrypt(userKey, iv, cipherText, 0, cipherText.length);
    }

    /**
     * @brief 현재 Manager가 사용하는 알고리즘 이름을 반환합니다.
     * @return 알고리즘 이름 "SEED".
     */
    @Override
    public String getAlgorithm() {
        return ALGORITHM_NAME;
    }

    public void setSeedInfo(KISA_SEED_INFO seedInfo) {
        this.seedInfo = seedInfo;
    }
    
    private KISA_SEED_INFO initializeSeedInfo(byte[] key, byte[] iv) {
        KISA_SEED_INFO info = new KISA_SEED_INFO();
        KISA_SEED_CBC.SEED_CBC_init(info, KISA_ENC_DEC.KISA_DECRYPT, key, iv);
        return info;
    }
}
