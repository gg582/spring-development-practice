package com.example.app.crypto.algorithms;

public interface DecryptManager {
    public byte[] decryptBytes(byte[] data, javax.crypto.SecretKey key) throws Exception;

    public String getAlgorithm();
}
