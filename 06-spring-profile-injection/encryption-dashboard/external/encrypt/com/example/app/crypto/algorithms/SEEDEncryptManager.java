package com.example.app.crypto.algorithms;

import com.example.app.crypto.algorithms.EncryptManager;
import com.example.app.crypto.algorithms.KISA_SEED_CBC; // KISA_SEED_CBC 클래스를 사용할 수 있다고 가정합니다.
import com.example.app.crypto.algorithms.KISA_SEED_CBC.KISA_SEED_INFO;
import com.example.app.crypto.algorithms.KISA_SEED_CBC.KISA_ENC_DEC;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * @brief SEED 암호화 관리자 구현체.
 * 
 * KISA_SEED_CBC 클래스를 사용하여 SEED 알고리즘으로 데이터를 암호화합니다.
 * 이 클래스는 EncryptManager 인터페이스를 구현하며, SEED 암호화 기능을 제공합니다.
 */
public class SEEDEncryptManager implements EncryptManager {

    private static final String ALGORITHM_NAME = "SEED"; // 알고리즘 이름
    private static final int KEY_SIZE_BYTES = 16; // SEED는 128비트 (16바이트) 키를 사용합니다.
    private static final int IV_SIZE_BYTES = 16; // SEED CBC 모드는 128비트 (16바이트) IV를 사용합니다.

    private KISA_SEED_INFO seedInfo; // SEED 암호화/복호화 상태 정보

    /**
     * @brief SEEDEncryptManager의 생성자.
     */
    public SEEDEncryptManager() {
        this.seedInfo = new KISA_SEED_INFO(); // SeedInfo 객체 초기화
        // 자바에서 동적으로 SEED Key와 IV를 생성합니다.
        byte[] defaultKey = new byte[KEY_SIZE_BYTES];
        byte[] defaultIV = new byte[IV_SIZE_BYTES];
        SecureRandom random = new SecureRandom();
        random.nextBytes(defaultKey);
        random.nextBytes(defaultIV);
    }

    /**
     * @brief 데이터를 SEED 알고리즘으로 암호화합니다.
     *
     * @param data 암호화할 원본 데이터 바이트 배열.
     * @param key 암호화에 사용할 SecretKey 객체.
     * @return 암호화된 데이터 바이트 배열.
     * @throws Exception 암호화 과정에서 오류 발생 시.
     */
    @Override
    public byte[] encryptBytes(byte[] data, SecretKey key) throws Exception {
        // SecretKey 객체에서 바이트 배열 키를 추출합니다.
        byte[] userKey = key.getEncoded();
        if (userKey.length != KEY_SIZE_BYTES) {
            // 키 크기를 맞추기 위해 패딩 또는 자르기를 수행할 수 있지만, 예외를 던지는 것이 안전합니다.
            byte[] paddedKey = new byte[KEY_SIZE_BYTES];
            System.arraycopy(userKey, 0, paddedKey, 0, Math.min(userKey.length, KEY_SIZE_BYTES));
            userKey = paddedKey;
        }

        // 자바에서 동적으로 IV를 생성합니다.
        byte[] iv = new byte[IV_SIZE_BYTES];
        new SecureRandom().nextBytes(iv);

        // KISA_SEED_CBC의 SEED_CBC_Encrypt 메소드를 호출합니다.
        byte[] encrypted = KISA_SEED_CBC.SEED_CBC_Encrypt(userKey, iv, data, 0, data.length);
        
        // 동적으로 생성된 IV를 암호문 앞에 붙여서 반환합니다.
        byte[] result = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(encrypted, 0, result, iv.length, encrypted.length);
        
        return result;
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
        KISA_SEED_CBC.SEED_CBC_init(info, KISA_ENC_DEC.KISA_ENCRYPT, key, iv);
        return info;
    }
}
