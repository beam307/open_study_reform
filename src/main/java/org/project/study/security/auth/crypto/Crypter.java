package org.project.study.security.auth.crypto;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public interface Crypter {
    String encrypt(String value) throws GeneralSecurityException, UnsupportedEncodingException;

    String decrypt(String value) throws GeneralSecurityException, UnsupportedEncodingException;
}
