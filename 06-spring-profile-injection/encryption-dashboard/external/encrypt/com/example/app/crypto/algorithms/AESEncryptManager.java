package com.example.app.crypto.algorithms;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;

public class AESEncryptManager implements EncryptManager {

  @Override
  public String getAlgorithm() {
    return "AES";
  }

  // AES 암호화는 여기에서 수행합니다
  @Override
  public byte[] encryptBytes(byte[] data, SecretKey key) throws Exception /* 더 정교한 예외 처리가 동반되어야 합니다 */ {
    byte[] iv = new byte[16];
    SecureRandom random = new SecureRandom(); // 단순히 Unix Timestamp에만 의존하는 랜덤보다 강력합니다. 보안이 중요할 때는 반드시 이러한 것을 사용합니다.
    random.nextBytes(iv);

    IvParameterSpec ivParamSpec = new IvParameterSpec(iv);

    // Cipher를 초기화합니다.
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // JCE 표준 변환 문자열을 사용합니다.
    cipher.init(Cipher.ENCRYPT_MODE, key, ivParamSpec); // ENCRYPT_MODE와 DECRYPT_MODE 두 가지가 있습니다.
    // 암호화를 수행해 줍니다.
    byte[] encrypted = cipher.doFinal(data);

    // IV와 암호화 데이터를 결합합니다.
    // 실제로 보안을 중시해야 한다면 IV를 단순히 앞에 붙이지 말고
    // 적재적소에 숨길 필요가 있습니다.
    // 단순하게는 offset이 있고 더 정밀하게는
    // IV를 n등분해서 다양한 offset 위치에 숨길 수도 있습니다
    byte[] result = new byte[iv.length + encrypted.length];
    System.arraycopy(iv, 0, result, 0, iv.length);
    // 이 경우 실제 데이터를 읽기 위한 offset은 iv.length만큼 주어야 하나
    // 이 흐름에선 iv를 읽은 직후 그 위치부터 자연스럽게 읽기 때문에
    // 그다지 오프셋에 대해 고민할 필요는 없습니다.
    // 다만 실제 프로덕션 코드에서 이러한 단순한 시나리오는 없으니 주의가 필요합니다.
    System.arraycopy(encrypted, 0, result, iv.length, encrypted.length);
    return result;
  }
}
