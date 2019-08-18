package org.project.study.security.exception;

import org.project.study.security.auth.token.JwtToken;
import org.springframework.security.core.AuthenticationException;

public class JwtExpiredTokenException extends AuthenticationException {

    private JwtToken token;


    public JwtExpiredTokenException(JwtToken token, String msg, Throwable t) {
        super(msg, t);
        this.token = token;
    }

    public JwtExpiredTokenException(String msg) {
        super(msg);
    }

    public String token() {
        return this.token.getToken();
    }
}
