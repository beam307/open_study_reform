package org.project.study.security.auth.crypto;

import org.apache.commons.lang3.ArrayUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class JcaCrypter implements Crypter {

    private String key;

    public JcaCrypter(String key) {
        this.key = key;
    }

    @Override
    public String encrypt(String value) throws GeneralSecurityException, UnsupportedEncodingException {
        SecretKeySpec keySpec = secretKeyWithSha256(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encData = cipher.doFinal(value.getBytes("UTF-8"));
        byte[] iv = cipher.getIV();
        if (iv == null) {
            throw new GeneralSecurityException("Underlying JCE iv bug");
        }

        return String.format("%d-%s", 1, Base64.getEncoder().encodeToString(ArrayUtils.addAll(iv, encData)));
    }

    @Override
    public String decrypt(String value) throws GeneralSecurityException, UnsupportedEncodingException {
        String[] splitted = value.split("-");

        if (splitted.length != 2 || !splitted[0].equals("1")) {
            throw new GeneralSecurityException("invalid format");
        }

        return decryptVersion1(splitted[1], key);
    }

    private SecretKeySpec secretKeyWithSha256(String privateKey, String algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(privateKey.getBytes("UTF-8"));

        int maxAllowedKeyLength = Cipher.getMaxAllowedKeyLength(algorithm) / 8;

        byte[] digested = messageDigest.digest();
        byte[] raw = digested.length <= maxAllowedKeyLength ? digested : Arrays.copyOfRange(digested, 0, maxAllowedKeyLength);
        return new SecretKeySpec(raw, algorithm);
    }

    private String decryptVersion1(String value, String privateKey) throws GeneralSecurityException, UnsupportedEncodingException {
        byte[] data = Base64.getDecoder().decode(value);
        SecretKeySpec keySpec = secretKeyWithSha256(privateKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        int blockSize = cipher.getBlockSize();
        byte[] iv = Arrays.copyOfRange(data, 0, blockSize);
        byte[] payload = Arrays.copyOfRange(data, blockSize, data.length);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));

        return new String(cipher.doFinal(payload), "UTF-8");
    }
}
