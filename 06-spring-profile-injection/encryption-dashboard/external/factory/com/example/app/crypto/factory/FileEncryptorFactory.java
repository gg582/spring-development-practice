package com.example.app.crypto.factory;

import com.example.app.crypto.FileEncryptor;
import com.example.app.crypto.algorithms.AESEncryptManager;
import com.example.app.crypto.algorithms.EncryptManager;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @brief 파일 암호화 객체를 생성하는 팩토리 클래스.
 *
 * Spring의 DI를 통해 EncryptManager를 주입받아 파일 암호화 기능을 제공합니다.
 * 프로파일 설정을 통해 AES, Chacha, SEED 등 다양한 암호화 알고리즘을 동적으로 선택할 수 있습니다.
 */
public class FileEncryptorFactory {
    // 기본적으로 AES 암호화 관리자를 사용합니다.
    // Spring Profile 설정에 따라 다른 Manager가 주입될 수 있습니다.
    private EncryptManager encryptManager = new AESEncryptManager();

    /**
     * @brief EncryptManager를 주입받는 Setter 메서드.
     * 
     * Spring Bean 설정에서 "encryptManager" 프로퍼티에 사용할 Manager Bean을 참조하여 주입합니다.
     * 예: profile="aes" 일 경우 AESEncryptManager, profile="seed" 일 경우 SEEDEncryptManager 등이 주입됩니다.
     * @param encryptManager 주입받을 EncryptManager 구현체.
     */
    public void setEncryptManager(EncryptManager encryptManager) {
        this.encryptManager = encryptManager;
    }

    /**
     * @brief FileEncryptor 객체를 생성하여 반환합니다.
     * 
     * 현재 주입된 encryptManager를 사용하여 실제 암호화 로직을 수행하는 FileEncryptor 객체를 생성합니다.
     * @return 생성된 FileEncryptor 객체.
     */
    public FileEncryptor createFileEncryptor() {
        // 익명 내부 클래스로 FileEncryptor 구현체를 생성합니다.
        return new FileEncryptor() {
            // Factory에서 주입받은 Manager를 사용합니다.
            private EncryptManager manager = encryptManager;

            /**
             * @brief 실제 파일 암호화 로직을 수행합니다.
             *
             * @param sourcePath 원본 파일 경로.
             * @param targetPath 암호화된 파일 저장 경로.
             * @throws Exception 파일 읽기/쓰기 또는 암호화 과정에서 오류 발생 시.
             */
            @Override
            public void encryptFile(String sourcePath, String targetPath) throws Exception {
                byte[] data = Files.readAllBytes(Paths.get(sourcePath));
                // TODO: 실제 환경에 맞는 키 관리 로직 필요 (임시 16바이트 SecretKey 사용)
                // SecretKey 객체는 Bean 설정에서 관리하는 것을 권장합니다.
                SecretKey key = new SecretKeySpec(new byte[16], manager.getAlgorithm());
                byte[] encrypted = manager.encryptBytes(data, key);
                Files.write(Paths.get(targetPath), encrypted);
            }

            /**
             * @brief 현재 사용 중인 암호화 알고리즘을 확인하는 메서드.
             * (디버깅 및 테스트 용도)
             */
            @Override
            public void dummyCheck() {
                System.out.println("Encryptor Ready: " + manager.getAlgorithm());
            }

            /**
             * @brief EncryptManager를 변경합니다.
             * (런타임 시 동적 변경이 필요한 경우 사용)
             * @param m 변경할 EncryptManager 객체.
             */
            @Override
            public void setEncryptManager(EncryptManager m) {
                this.manager = m;
            }

            /**
             * @brief 현재 설정된 EncryptManager를 반환합니다.
             * @return 현재 사용 중인 EncryptManager 객체.
             */
            @Override
            public EncryptManager getEncryptManager() {
                return this.manager;
            }
        };
    }
}
