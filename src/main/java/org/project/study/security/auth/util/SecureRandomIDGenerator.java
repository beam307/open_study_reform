package org.project.study.security.auth.util;

import org.springframework.security.jwt.codec.Codecs;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class SecureRandomIDGenerator {

    private SecureRandom random = new SecureRandom();

    public String generate() {
        byte[] val = new byte[128];
        random.nextBytes(val);

        return new String(Codecs.hexEncode(val));
    }
}
