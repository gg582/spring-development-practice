package com.example.app.crypto.algorithms;

import javax.crypto.SecretKey;

public interface EncryptManager {
	public byte[] encryptBytes(byte[] data, SecretKey key) throws Exception;
	public String getAlgorithm();
}
