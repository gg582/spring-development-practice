package com.example.decrypt.algorithms;

public interface DecryptManager {
    public byte[] decryptBytes(byte[] data, javax.crypto.SecretKey key) throws Exception;
}
