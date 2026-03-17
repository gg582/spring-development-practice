package com.example.encrypt.algorithms;

import javax.crypto.SecretKey;

public interface EncryptManager {
	public byte[] encryptBytes(byte[] data, SecretKey key) throws Exception;

	public String getAlgorithm();
}