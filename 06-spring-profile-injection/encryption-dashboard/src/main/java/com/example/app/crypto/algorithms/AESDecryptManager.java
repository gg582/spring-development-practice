package com.example.app.crypto.algorithms;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class AESDecryptManager implements DecryptManager {
    @Override
    public String getAlgorithm() {
        return "AES";
    }

    // AES 복호화는 여기에서 수행합니다
    @Override
    public byte[] decryptBytes(byte[] data, SecretKey key) throws Exception /* 더 정교한 예외 처리가 동반되어야 합니다 */ {
        /**
         * 이것은 실습 코드이기에 이 정도로만 수행하나,
         * 실제 암호화 상황에 대해서는 조금 다릅니다.
         * 보통 실제 암호화 상황에서는 IV 등의 예민한 정보는 잘게 쪼개서 오프셋을 두어 저장하거나
         * 여러 겹의 키 보호 알고리즘이 들어가 곧바로 IV를 읽을 수 없습니다.
         * Key가 노출되는 것 못지 않게 IV의 노출은 잘 관리되어야 합니다.
         * Key와 IV는 노출되어서는 안 되는 상자의 열쇠나 자물쇠같은 역할입니다.
         **/

        // IV 추출
        byte[] iv = new byte[16];
        byte[] encryptedData = new byte[data.length - iv.length];
        System.arraycopy(data, 0, iv, 0, iv.length);
        System.arraycopy(data, iv.length, encryptedData, 0, encryptedData.length);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // 암호화된 데이터 추출.
        // 최대 길이는 총 데이터 길이 - iv임.
        // 시작 offset이 16이다.
        // offset 위치부터 data에서 읽어서 decrypted 배열의 처음 위치부터 복사한다.
        // 결과적으로 decrypted 배열은 순수한 암호화된 데이터를 갖게 된다.
        byte[] decrypted = encryptedData;

        // Cipher 초기화를 진행한다. 방법은 AES/CBC/PKCS7Padding으로 암호화 시 사용한 것과 동일하다.
        // 따라서 이 웹을 이용하지 않고 암호화된 것은 포맷이 다르면 해제 불가하다.
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);

        // 복호화를 수행한다.
        return cipher.doFinal(decrypted);

    }
}
