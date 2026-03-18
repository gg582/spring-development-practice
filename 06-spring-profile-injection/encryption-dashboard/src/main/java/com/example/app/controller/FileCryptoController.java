package com.example.app.controller;

import java.util.HashMap;
import java.util.Collections;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.app.service.FileService;

public class FileCryptoController {
    public ResponseEntity<Map<String, Object>> encryptFile(Map<String, String> request) {
        String content = request.get("content");
        String b64Password = request.get("password");

        try {
            FileService fileService = new FileService();
            byte[] encryptedData = fileService.encryptFile(content.getBytes(), b64Password);
            String b64EncryptedData = java.util.Base64.getEncoder().encodeToString(encryptedData);

            Map<String, Object> response = new HashMap<>();
            // 암호화된 데이터를 Base64로 인코딩하여 반환합니다. 실제로는 파일 다운로드 링크를 제공하는 방식이 더 일반적입니다.
            // 추후 리팩토링 시 b64EncryptedData를 로컬에 저장하고 다운로드 링크를 제공하게 변경해야 합니다.
            response.put("encryptedData", b64EncryptedData);
            response.put("algorithm", fileService.getEncryptManager().getAlgorithm());
            response.put("result", "success");
            response.put("message", "File encrypted successfully.");
            response.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // 실제로는 더 정교한 예외 처리가 필요합니다.
            return ResponseEntity.status(500)
                    .body(Collections.singletonMap("error", "Encryption failed: " + e.getMessage()));
        }
    }
}
